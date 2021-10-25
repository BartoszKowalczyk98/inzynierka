package com.Bartosz.vCard;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VCard {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index", "index", new SearchItem());
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE + "; " +
			"charset=utf-8")
	public void showCards(@ModelAttribute("results") SearchItem searchItem, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DataFromJson> listOfOffers = getJobs(searchItem.getK(), searchItem.getL());

		request.setAttribute("listOfOffers", listOfOffers);
		request.setAttribute("vcardinfo", new DataFromJson());
		request.getRequestDispatcher("/WEB-INF/jsp/results.jsp").forward(request, response);

	}
	@RequestMapping(value = "/generate-vcard", method = RequestMethod.POST)
	public ResponseEntity<Resource> generatevCard(@ModelAttribute("vcardinfo") DataFromJson dataFromJson,
												  BindingResult result,
												  ModelMap map)
			throws IOException {
		File file = new File("vcard.vcf");
		FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("BEGIN:VCARD\r\n");
		bufferedWriter.write("VERSION:4.0\r\n");
		if (dataFromJson.getName() != "") {
			bufferedWriter.write("ORG:" + dataFromJson.getName() + "\r\n");
		}
		if (dataFromJson.getTelephone() != "") {
			bufferedWriter.write("TEL:" + dataFromJson.getTelephone() + "\r\n");
		}
		if (dataFromJson.getAddressAsString() != "") {
			bufferedWriter.write("ADR:" + dataFromJson.getAddressAsString() + "\r\n");
		}
		if (dataFromJson.getEmail() != "") {
			bufferedWriter.write("EMAIL:" + dataFromJson.getEmail() + "\r\n");
		}
		if (dataFromJson.getUrl() != "") {
			bufferedWriter.write("URL:" + dataFromJson.getUrl() + "\r\n");
		}
		bufferedWriter.write("END:VCARD");
		bufferedWriter.close();
		Resource fileSystemResource = new FileSystemResource(file);
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType("text/vcard"))
				.body(fileSystemResource);
	}
	//https://panoramafirm.pl/szukaj?k=hydraulik&l=
	public List<DataFromJson> getJobs(String searchitem, String localization) {
		//jsoup beginners guide taken from https://jsoup.org/cookbook/input/load-document-from-url
		List<DataFromJson> listOfOffers = new ArrayList<>();

		try {
			StringBuilder stringBuilder = new StringBuilder();
			if (searchitem == null || searchitem.isBlank())
				return listOfOffers;

			stringBuilder.append("https://panoramafirm.pl/szukaj?k=").append(searchitem).append("&l=");

			if (localization != null && !localization.isEmpty())
				stringBuilder.append(localization);

			Document doc = Jsoup.connect(stringBuilder.toString()).get();
			Elements jsons = doc.select("script");
			Gson gson = new Gson();

			for (Element element : jsons) {
				if (element.attr("type").equals("application/ld+json") && element.data().contains("Address")) {
					DataFromJson singleCompanyInfo = gson.fromJson(element.data(), DataFromJson.class);
					listOfOffers.add(singleCompanyInfo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfOffers;
	}

}

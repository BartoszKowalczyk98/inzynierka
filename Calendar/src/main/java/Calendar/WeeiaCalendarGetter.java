package Calendar;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.UidGenerator;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WeeiaCalendarGetter {
	@RequestMapping(method = RequestMethod.GET)
	public void getWeeiaCalendar(@RequestParam("year") String year, @RequestParam("month") String month,
							  HttpServletResponse response) {
		//jsoup beginners guide taken from https://jsoup.org/cookbook/input/load-document-from-url
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=").append(year).append("&miesiac=");
			if (month.length() == 1) {
				month = "0" + month;
			}
			stringBuilder.append(month).append("&lang=1");
			Document doc = Jsoup.connect(stringBuilder.toString()).get();
			Elements tds = doc.select("td");
			List<DataHolderClass> dataHolderClasses = new ArrayList<>();
			filterDataFromWeeia(year, month, tds, dataHolderClasses);

			/*
			Create calendar and input some basic data inside most of code below was taken from:
			https://ical4j.github.io/ical4j-user-guide/examples/
			 */
			Calendar calendar = getCalendar();

			// Generate a UID for the event..
			addEventsToCalendar(dataHolderClasses, calendar);

			createAndReturnFile(response, calendar);

		} catch (IOException | ParseException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	class DataHolderClass {
		private String month;
		private String day;
		private String year;
		private String url;
		private String title;

		public DataHolderClass(String year, String month, String day, String url, String title) {
			this.month = month;
			if (day.length() == 1) {
				this.day = "0" + day;
			} else
				this.day = day;
			this.year = year;
			this.url = url;
			this.title = title;
		}
	}
	private void createAndReturnFile(HttpServletResponse response, Calendar calendar) throws IOException {
		//Creating and filling ics file with our calendar
		FileOutputStream fout = new FileOutputStream("mycalendar.ics");

		CalendarOutputter outputter = new CalendarOutputter();
		outputter.output(calendar, fout);

		//download file as response from server
		InputStream inputStream = new FileInputStream(new File("mycalendar.ics"));
		response.setContentType("text/calendar;charset=utf-8");
		IOUtils.copy(inputStream, response.getOutputStream());
		response.flushBuffer();
	}

	private void filterDataFromWeeia(String year, String month, Elements tds, List<DataHolderClass> dataHolderClasses) {
		for (Element td : tds) {
			if(td.attr("class").equals("active")){
				Elements p = td.getElementsByTag("p");
				Element a = td.child(0);
				String url = a.attr("href");

				dataHolderClasses.add(new DataHolderClass(year, month, a.text(), url, p.text()));
			}
		}
	}

	private void addEventsToCalendar(List<DataHolderClass> dataHolderClasses, Calendar calendar) throws ParseException, URISyntaxException {
		UidGenerator ug = () -> new Uid("test@example.com");
		for (DataHolderClass event : dataHolderClasses) {
			// initialise as an all-day event..
			String dateInString = event.year + event.month + event.day;
			VEvent vEvent = new VEvent(new Date(dateInString), event.title);
			vEvent.getProperties().add(ug.generateUid());
			Url url = new Url();
			url.setValue(event.url);
			vEvent.getProperties().add(url);
			calendar.getComponents().add(vEvent);
		}
	}

	private Calendar getCalendar() {
		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Bartosz Kowalczyk//iCal4j 1.0//PL"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
		return calendar;
	}

}

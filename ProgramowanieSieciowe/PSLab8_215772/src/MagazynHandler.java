import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MagazynHandler implements HttpHandler {
    int port;
    float suma;

    public MagazynHandler(int port) {
        this.port = port;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String response;
        suma = 0;
        if (requestURI.toString().equals("/") || requestURI.toString().contains("favicon"))
            response = "<tr><td>Powinienes dodac nr magazynu do adresu URL</tr></td>";
        else {
            try {
                response = readMagazyn(requestURI.getPath());
            } catch (IOException e) {
                response = "<tr><td>Albo magazyn nie istnieje albo podales blednie adres url</tr></td>";
            }
        }
        response = getHtmlFromString(response);
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(),0,response.length());
        os.close();

    }

    private String getHtmlFromString(String response) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>\n").
                append("<head>").
                append("<link rel=\"shortcut icon\" href=\"\">").
                append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n").
                append("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\"></script>\n").
                append("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>\n").
                append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\n").
                append("</head>").
                append("<body>\n").
                append("<table class=\"table\">\n").
                append(response).
                append("</table>\n").
                append("Suma = ").
                append(suma).
                append("\n</body>\n").
                append("</html>\n");
        return htmlBuilder.toString();
    }

    private String readMagazyn(String urlPath) throws IOException {
        List<Integer> nrMagazynu = new ArrayList<>();
        String[] strings = urlPath.substring(1).split("_");
        for (String s : strings) {
            try {
                nrMagazynu.add(Integer.parseInt(s));
            } catch (NumberFormatException exception) {
                System.out.println(exception.getMessage());
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : nrMagazynu) {
            File dir = new File("src/magazyny/" + integer + ".txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dir));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.contains("towar") && line.contains("<td>")) {
                    String digits = line.replaceAll("[^0-9.]", "");
                    suma += Integer.parseInt(digits);
                }
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

}

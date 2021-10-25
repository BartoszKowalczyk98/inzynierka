import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class URLReader {
    private int howMany = 12;
    private URL url;


    public URLReader(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public HashSet<String> readFromURL() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        HashSet<String> result = new HashSet<>();
        ArrayList<String> all = new ArrayList<>();
        while ((inputLine = bufferedReader.readLine()) != null) {
            all.add(inputLine);
        }
        for (int i = 0; i < howMany; i++) {
            String temp = all.get(new Random().nextInt(all.size()));
            if (result.contains(temp))
                i--;
            else
                result.add(temp);

        }

        bufferedReader.close();
        return result;
    }
}

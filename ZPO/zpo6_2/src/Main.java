import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main{
    public static void main(String [] Args){
        try {
            List<String> all = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\Bartosz\\IdeaProjects\\zpo6_2\\src\\test.txt")));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                all.add(inputLine);
            }
            Map<String,Integer> mapatestowa = new HashMap<>();
            SlownikUpdater.updateprzezputIfAbsent(mapatestowa,all);
            System.out.println(mapatestowa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

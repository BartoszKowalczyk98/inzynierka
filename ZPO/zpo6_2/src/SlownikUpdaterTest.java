import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SlownikUpdaterTest {

    @org.junit.jupiter.api.Test
    void testWszystkichFunkcji() {
        try {
            List<String> all = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\Bartosz\\IdeaProjects\\zpo6_2\\src\\test.txt")));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                all.add(inputLine);
            }
            Map<String,Integer> mapatestowaget = new HashMap<>();
            Map<String,Integer> mapatestowacontains = new HashMap<>();
            Map<String,Integer> mapatestowadefault = new HashMap<>();
            Map<String,Integer> mapatestowaput = new HashMap<>();

            SlownikUpdater.updatePrzezGet(mapatestowaget,all);
            SlownikUpdater.updatePrzezContainskey(mapatestowacontains,all);
            SlownikUpdater.updatePrzezgetOrDefault(mapatestowadefault,all);
            SlownikUpdater.updateprzezputIfAbsent(mapatestowaput,all);

            assertTrue(mapatestowaget.equals(mapatestowacontains));
            assertTrue(mapatestowaget.equals(mapatestowadefault));
            assertTrue(mapatestowaget.equals(mapatestowaput));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
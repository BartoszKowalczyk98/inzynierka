import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void formattedNumbersTEST() {
        List<Double> li = new ArrayList<>();
        Collections.addAll(li, (double) -5100, 43.257, (double) 200000, 2000000.5);
        List<String> fn = Main.formattedNumbers(li, 2, ',', 2, true);
        assertTrue(fn.size() == 4);
        assertTrue(fn.get(0).equals("    -51,00.00"));
        assertTrue(fn.get(1).equals("        43.26"));
        assertTrue(fn.get(2).equals("  20,00,00.00"));
        assertTrue(fn.get(3).equals("2,00,00,00.50"));
        List<String> fn2 = Main.formattedNumbers(li, 3, '|', 2,false);
        assertTrue(fn2.size() == 4);
        assertTrue(fn2.get(0).equals("   -5|100"));
        assertTrue(fn2.get(1).equals("       43.26"));
        assertTrue(fn2.get(2).equals("  200|000"));
        assertTrue(fn2.get(3).equals("2|000|000.5"));
    }
}
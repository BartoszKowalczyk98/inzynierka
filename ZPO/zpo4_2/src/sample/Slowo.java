package sample;
import java.util.ArrayList;
import java.util.Collections;

public class Slowo {
    String poPolsku;
    String [] arrSlow;
    ArrayList<String> poAngielsku = new ArrayList<>();

    public Slowo(String poPolsku, String[] arrSlow) {
        this.poPolsku = poPolsku;
        this.arrSlow = arrSlow;
        Collections.addAll(poAngielsku,arrSlow);
    }
}

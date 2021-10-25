import ksiazki.Ksiazka;
import wydawnictwa.Wydawnictwo;

public class Main {
    public static void main(String [] args){
        Wydawnictwo w = Wydawnictwo.getInstance("Józef Ignacy Kraszewski");
        Ksiazka k = w.createBook("Masław", 280);
        System.out.println(k);
    }
}

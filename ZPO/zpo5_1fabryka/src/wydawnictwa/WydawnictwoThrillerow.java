package wydawnictwa;

import ksiazki.Ksiazka;
import ksiazki.Thriller;

public class WydawnictwoThrillerow extends Wydawnictwo {
    String autor;

    public WydawnictwoThrillerow(String autor) {
        this.autor = autor;
    }

    @Override
    public Ksiazka createBook(String tytul, int liczbastron) {
        return new Thriller(autor,tytul,liczbastron);
    }
}

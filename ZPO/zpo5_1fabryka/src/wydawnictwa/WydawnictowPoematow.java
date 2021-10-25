package wydawnictwa;

import ksiazki.Ksiazka;
import ksiazki.Poemat;
import ksiazki.PowiescHistoryczna;

public class WydawnictowPoematow extends Wydawnictwo{
    String autor;

    public WydawnictowPoematow(String autor) {
        this.autor = autor;
    }

    @Override
    public Ksiazka createBook(String tytul, int liczbastron) {
        return new Poemat(autor,tytul,liczbastron);
    }
}

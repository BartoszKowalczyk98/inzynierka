package wydawnictwa;

import ksiazki.Ksiazka;
import ksiazki.PowiescHistoryczna;

public class WydawnictwoPowiesciHistorycznych extends  Wydawnictwo {
    String autor;

    public WydawnictwoPowiesciHistorycznych(String autor) {
        this.autor = autor;
    }

    @Override
    public Ksiazka createBook(String tytul, int liczbastron) {
        return new PowiescHistoryczna(autor,tytul,liczbastron);
    }
}

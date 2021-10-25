package ksiazki;

public class Thriller extends Ksiazka {
    public Thriller(String autor, String tytul, int iloscstron) {
        super(autor, tytul, iloscstron);
    }

    @Override
    public String toString() {
        return super.toString() + " thriller |";
    }
}

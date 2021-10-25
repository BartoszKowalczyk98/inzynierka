package ksiazki;

public class Poemat extends Ksiazka {
    public Poemat(String autor, String tytul, int iloscstron) {
        super(autor, tytul, iloscstron);
    }

    @Override
    public String toString() {
        return super.toString() + " poemat |";
    }
}

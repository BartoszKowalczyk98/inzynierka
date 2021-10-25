package ksiazki;

public class PowiescHistoryczna extends Ksiazka {
    public PowiescHistoryczna(String autor, String tytul, int iloscstron) {
        super(autor, tytul, iloscstron);
    }

    @Override
    public String toString() {
        return super.toString() + "powiesc historyczna |";
    }
}

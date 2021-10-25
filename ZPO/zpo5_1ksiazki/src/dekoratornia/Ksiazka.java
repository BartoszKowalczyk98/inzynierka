package dekoratornia;

public class Ksiazka implements Publikacja {
    String autor;
    String tytul;
    int iloscstron;

    public Ksiazka(String autor, String tytul, int iloscstron) {
        this.autor = autor;
        this.tytul = tytul;
        this.iloscstron = iloscstron;
    }

    @Override
    public String getAutor() {
        return " | " + autor;
    }

    @Override
    public String getTytul() {
        return " | " + tytul + " | ";
    }

    @Override
    public int getIloscStron() {
        return iloscstron;
    }

    @Override
    public String toString() {
        return getAutor() + getTytul() + getIloscStron() + " | ";
    }
}

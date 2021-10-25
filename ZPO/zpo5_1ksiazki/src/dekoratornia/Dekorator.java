package dekoratornia;

public class Dekorator implements Publikacja {
    Publikacja publikacja;

    public Dekorator(Publikacja publikacja) {
        this.publikacja = publikacja;
    }

    @Override
    public String getAutor() {
        return publikacja.getAutor();
    }

    @Override
    public String getTytul() {
        return publikacja.getTytul();
    }

    @Override
    public int getIloscStron() {
        return publikacja.getIloscStron();
    }
}

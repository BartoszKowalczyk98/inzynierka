package wydawnictwa;

import ksiazki.Ksiazka;

import java.util.ArrayList;

public abstract class Wydawnictwo {
    public static Wydawnictwo getInstance(String autor){
        ArrayList<String> historyczne = new ArrayList<>();
        historyczne.add("Józef Ignacy Kraszewski");
        historyczne.add("Johannes Sachslehner");
        historyczne.add("Anders Rydell");

        ArrayList<String> poematy = new ArrayList<>();
        poematy.add("Hezjod");
        poematy.add("T.S. Eliot");
        poematy.add("Juliusz Slowacki");

        ArrayList<String> thrillery = new ArrayList<>();
        thrillery.add("Tess Gerritsen");
        thrillery.add("Alex North");
        thrillery.add("Katarzyna Bonda");

        if(historyczne.contains(autor))
            return new WydawnictwoPowiesciHistorycznych(autor);
        else if(poematy.contains(autor))
            return new WydawnictowPoematow(autor);
        else if(thrillery.contains(autor))
            return new WydawnictwoThrillerow(autor);
        return null;
    }
    public abstract Ksiazka createBook(String tytul, int liczbastron);
}

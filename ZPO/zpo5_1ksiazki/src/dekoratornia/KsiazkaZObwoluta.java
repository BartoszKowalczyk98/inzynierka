package dekoratornia;

import mojeWyjatki.ObwolutkaBezOkladkiException;
import mojeWyjatki.TylkoJednaObwolutkaException;

public class KsiazkaZObwoluta extends Dekorator{

    public KsiazkaZObwoluta(Publikacja publikacja) throws TylkoJednaObwolutkaException, ObwolutkaBezOkladkiException {
        super(publikacja);
        if(publikacja instanceof KsiazkaZObwoluta)
            throw new TylkoJednaObwolutkaException();
        else if(!(publikacja instanceof  KsiazkaZOkladkaZwykla || publikacja instanceof  KsiazkaZOkladkaTwarda))
            throw new ObwolutkaBezOkladkiException();
    }

    @Override
    public String toString() {
        return publikacja.toString() + " | z obwolutą |";
    }
}

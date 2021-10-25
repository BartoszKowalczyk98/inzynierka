package dekoratornia;

import mojeWyjatki.ZaDuzoAutografowException;

public class KsiazkaZAutografem extends Dekorator {
    String autograf;
    public KsiazkaZAutografem(Publikacja publikacja, String autograf)  throws ZaDuzoAutografowException {
        super(publikacja);
        this.autograf = autograf;
        if(publikacja instanceof KsiazkaZAutografem)
            throw new ZaDuzoAutografowException();
    }

    @Override
    public String toString() {
        return publikacja.toString() + " | "+ autograf +" | ";
    }
}

package dekoratornia;

import mojeWyjatki.TylkoJednaOkladkaException;

public class KsiazkaZOkladkaTwarda extends Dekorator{

    public KsiazkaZOkladkaTwarda(Publikacja publikacja) throws TylkoJednaOkladkaException {
        super(publikacja);
        if(publikacja instanceof KsiazkaZOkladkaTwarda || publikacja instanceof KsiazkaZOkladkaZwykla)
            throw new TylkoJednaOkladkaException();
    }

    @Override
    public String toString() {
        return publikacja.toString() +" | w twardej okladce |";
    }
}

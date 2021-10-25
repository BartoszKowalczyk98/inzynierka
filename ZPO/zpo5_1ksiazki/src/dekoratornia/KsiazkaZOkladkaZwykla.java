package dekoratornia;

import mojeWyjatki.TylkoJednaOkladkaException;

public class KsiazkaZOkladkaZwykla extends Dekorator {

    public KsiazkaZOkladkaZwykla(Publikacja publikacja) throws TylkoJednaOkladkaException {
        super(publikacja);
        if(publikacja instanceof KsiazkaZOkladkaZwykla || publikacja instanceof  KsiazkaZOkladkaTwarda)
            throw new TylkoJednaOkladkaException();
    }

    @Override
    public String toString() {
        return publikacja.toString()+ " | w zwyklej okladce |";
    }
}

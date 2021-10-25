import java.security.spec.RSAOtherPrimeInfo;
import java.util.Random;
import java.util.concurrent.Callable;

public class Osoba implements Callable<Osoba> {
    Random random;
    boolean ogladamfilm = false;
    Double pradopodobiensto;

    public Osoba(double pradopodobiensto) {
        this.random = new Random();
        this.pradopodobiensto = pradopodobiensto * 100.0;
    }


    @Override
    public Osoba call() throws Exception {
        try {

            Thread.sleep((random.nextInt(4) + 1) * 100);
            if (random.nextInt(101) < pradopodobiensto.intValue()) {
                ogladamfilm = true;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean isOgladamfilm() {
        return ogladamfilm;
    }
}

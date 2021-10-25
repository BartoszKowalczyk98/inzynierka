
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Logger;

public class KolarzTask extends TimerTask implements Comparable<KolarzTask> {
    String nazwisko;
    long czas;
    int stosunek = 25;
    //nextgaussian + srednia * odchylenie
    boolean koniec = false;
    Logger logger;

    public KolarzTask(String nazwisko ,Logger logger) {
        this.nazwisko = nazwisko;
        Random r = new Random();
        this.czas = (long) (r.nextGaussian() * 40 + 290);
        if (czas < 240)
            czas = 240;
        else if (czas > 350)
            czas = 350;
        this.logger = logger;
    }

    @Override
    public void run() {

        try {
            logger.info("Kolarz "+nazwisko+" wyjechał!");
            long czasSymulowany =  czas / stosunek;
            Thread.sleep(czasSymulowany * 1000);
            koniec = true;
            logger.info("Kolarz "+nazwisko+" skonczyl wyscig z czasem " + czas+"!");
        } catch (InterruptedException e) {
            System.out.println("kolarz mial wypadek!");
        }
    }

    @Override
    public int compareTo(KolarzTask k) {
        return (int) (this.czas - k.czas);
    }
}

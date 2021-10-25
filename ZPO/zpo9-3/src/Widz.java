import java.util.Random;
import java.util.concurrent.Callable;

public class Widz implements Callable<Widz> {
    Random random;
    Double prawdopodobienstwo;
    boolean zostajeogladac = false;
    int dlugoscfilmu;

    public Widz(Double prawdopodobienstwo, int dlugoscfilmu) {
        this.prawdopodobienstwo = prawdopodobienstwo * 100;
        this.dlugoscfilmu = dlugoscfilmu;
        random = new Random();
    }

    @Override
    public Widz call() throws Exception {
        Thread.sleep((dlugoscfilmu / 2) * 1000);
        if (random.nextInt(101) < prawdopodobienstwo.intValue()) {
            zostajeogladac = !zostajeogladac;
        }

        return null;
    }
}

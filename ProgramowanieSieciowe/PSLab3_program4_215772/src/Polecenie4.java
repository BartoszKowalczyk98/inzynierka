import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Polecenie4 {

    static int maxThreads = 10;


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<AlphabetIterator> alphabetIteratorList = new ArrayList<>();
        for (int i = 0; i < maxThreads; i++) {
            alphabetIteratorList.add(new AlphabetIterator(i + 1));
        }

        for (AlphabetIterator a : alphabetIteratorList) {
            executorService.submit(a);
        }
        new GUIAndLogic(alphabetIteratorList);

    }
}

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Polecenie3 {

    static int maxThreads = 10;


    public static void main(String[] args) {
        Object lock = new Object();
        List<AlphabetIterator> alphabetIteratorList = new CopyOnWriteArrayList<>();
        for (int threadIterator = 0; threadIterator < maxThreads; threadIterator++) {
            alphabetIteratorList.add(new AlphabetIterator(threadIterator + 1,lock));
            alphabetIteratorList.get(threadIterator).start();
        }
    }
}

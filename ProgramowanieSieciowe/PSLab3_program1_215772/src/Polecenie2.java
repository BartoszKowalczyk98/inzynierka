import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class Polecenie2 {

    static int maxThreads = 10;
    static GUIAndLogic guiAndLogic;

    public static void main(String[] args) {
        List<AlphabetIterator> alphabetIteratorList = new CopyOnWriteArrayList<>();
        for (int threadIterator = 0; threadIterator < maxThreads; threadIterator++) {
            alphabetIteratorList.add(new AlphabetIterator(threadIterator + 1));
            alphabetIteratorList.get(threadIterator).start();

        }
        guiAndLogic = new GUIAndLogic(alphabetIteratorList);

    }
}

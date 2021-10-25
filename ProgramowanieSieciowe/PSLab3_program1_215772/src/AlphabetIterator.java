public class AlphabetIterator extends Thread {
    private int id;
    public final Object lock = new Object();
    public boolean toRunOrNotToRun = false;
    char letter;

    public AlphabetIterator(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (letter = 'A'; letter <= 'Z'; letter++) {
            try {
                if (!toRunOrNotToRun) {
                    synchronized (lock) {
                        lock.wait();
                        toRunOrNotToRun = true;
                    }
                }
                System.out.printf("%c%d\n", letter, id);
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

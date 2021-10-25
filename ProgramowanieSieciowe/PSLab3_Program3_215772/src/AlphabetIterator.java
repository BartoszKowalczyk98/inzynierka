public class AlphabetIterator extends Thread {
    private int id;

    char letter;
    Object lock;
    public AlphabetIterator(int id,Object o) {
        this.id = id;
        this.lock = o;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (letter = 'A'; letter <= 'Z'; letter++) {
                try {
                    System.out.printf("%c%d\n", letter, id);
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

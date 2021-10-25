public class AlphabetIterator extends Thread {
    private int id;
    char letter;
    private boolean toRunOrNotToRun = true;

    public AlphabetIterator(int id) {
        this.id = id;
    }

    @Override
    public void interrupt() {
        System.out.println("Watek " + id + " przerwany!");
        toRunOrNotToRun = false;
        super.interrupt();
    }


    @Override
    public void run() {
        for (letter = 'A'; toRunOrNotToRun && letter <= 'Z'; letter++) {
            try {
                System.out.printf("%c%d\n", letter, id);
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.printf("");
            }
        }

    }
}

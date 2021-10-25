public class Polecenie1 {

    public static void main(String[] args) {
        try {
            Thread myThread = new MyThread();
            myThread.start();
            myThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello World!");
        }
    }
}

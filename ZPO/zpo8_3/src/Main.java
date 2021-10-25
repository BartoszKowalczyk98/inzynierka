import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    private static String[] strings = {"aaaaaaaaaaa", "bbbbb", "cc", "dddddddddddddddddddddddddddd"};

    static List<MyThreadingClass> queue = new CopyOnWriteArrayList<>();
    static Object lock = new Object();
    public static void main(String[] args) {

        for (String s : strings) {
            new MyThreadingClass(s,lock).start();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (queue.size() != 0) {
            for (MyThreadingClass t : queue) {
                synchronized (t) {
                    if (t.getState().equals(Thread.State.WAITING)) {
                        synchronized (t.lock) {
                            t.lock.notify();
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else break;
                }
            }
        }

    }

    private static class MyThreadingClass extends Thread {

        String tobewritten;
        protected Object lock ;

        public MyThreadingClass(String tobewritten , Object lock) {
            this.tobewritten = tobewritten;
            this.lock = lock;
        }

        @Override
        public void run() {
            queue.add(this);
            for (char c : tobewritten.toCharArray()) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(c);
                synchronized (lock){
                    lock.notify();
                }
            }
            queue.remove(this);

        }
    }

}

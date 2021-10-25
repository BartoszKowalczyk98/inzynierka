import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Serwer {
    static String[] names = {"Falk Boden", "Kaj Allan Olsen", "Ian Steel"};
    static Object lock = new Object();
    static List<String> ktoJuzNaFinishu = new ArrayList<>();
    static boolean[] lotneFinishe = {true,true,true,true,true};

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int littleHelper = 0;

        try (var listener = new ServerSocket(59001)) {
            while (true) {
                if (littleHelper == 3)
                    break;
                pool.execute(new Kolarzyk(listener.accept(), names[littleHelper++]));
            }
            Thread.sleep(1);//nie czaje czemu tutaj musi czekac ta jedna sekunde ale bez niej 3 kolarz nie startuje
            synchronized (lock) {
                lock.notifyAll();//start
            }
            pool.shutdown();
            while (!pool.isTerminated()){}//czekanie az kazdy sobie spokojnie dojedzie
            System.out.println(ktoJuzNaFinishu);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Kolarzyk implements Runnable {


        Socket socket;
        String name;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;


        public Kolarzyk(Socket socket, String name) {
            this.socket = socket;
            this.name = name;
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                synchronized (lock) {
                    lock.wait();// jakies czekanie
                }
                dataOutputStream.writeUTF("Start");
                if (dataInputStream.readUTF().equals("name")) {
                    dataOutputStream.writeUTF(name);
                }
                String komunikatzwrotny = dataInputStream.readUTF();
                while (!komunikatzwrotny.equals("koniec")) {
                    String[] helper = komunikatzwrotny.split(" ");
                    synchronized (lotneFinishe) {
                        if(lotneFinishe[Integer.valueOf(helper[1])-1]){
                            lotneFinishe[Integer.valueOf(helper[1])-1]=false;
                            System.out.println(name+" wygral lotny finisz nr "+helper[1]);
                        }
                    }
                    komunikatzwrotny=dataInputStream.readUTF();
                }
                synchronized (ktoJuzNaFinishu){
                    ktoJuzNaFinishu.add(name);
                }
                dataOutputStream.writeUTF(String.valueOf(ktoJuzNaFinishu.size()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}

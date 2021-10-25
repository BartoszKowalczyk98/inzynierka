import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import paczka8.*;

public class Server {


    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(59898)) {
            System.out.println("server postawiony");
            ExecutorService pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new notyfikator(listener.accept()));
            }
        }
    }

    private static class notyfikator implements Runnable {
        private Socket socket;
        notyfikator(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("polaczono: " + socket);
            try {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


                Notyfikacja notka;
                ArrayList<Notyfikacja> tabliczka = new ArrayList<>();

                do {

                    try {

                        notka = (Notyfikacja) ois.readObject();
                        tabliczka.add(notka);
                        System.out.println("przyjelo " + notka.tekst+ " od " + socket+ " data do odeslania: "+notka.data);

                        (new Timer()).schedule(new TimerTask() {
                            @Override
                            public void run() {

                                try {

                                    Date iksde = new Date();
                                    for (Notyfikacja cos : tabliczka) {
                                        System.out.println(iksde +" " +cos.data );
                                        try {
                                            if (cos.compareDate(iksde) == 1) {
                                                oos.writeObject(cos);
                                                System.out.println("do " + socket + " wyslalo: " + cos.getTekst());
                                                tabliczka.remove(cos);
                                                break;
                                            }
                                        }
                                        catch (DatowyException dex){ }

                                    }
                                }
                                catch (IOException ioex) {
                                    System.out.println("IOException w timertasku");
                                }
                            }
                        }, notka.data);
                    }
                    catch (Exception e){
                        System.out.println("problem w pobieraniu notki od klienta"+socket);
                        break;
                    }

                }while(true);

            }
            catch (IOException ioe) {
                System.out.println("Error IOException na " +socket);
            }
            catch (Exception e){
                System.out.println("error i nie wiadomo za bardzo jaki "+socket);
            }
            finally {
                try {
                    socket.close();
                }
                catch (IOException e) {
                    System.out.println("cos nie pyklo przy zamykaniu!");
                }
                System.out.println("rozlaczono: " + socket);
            }
        }
    }
}
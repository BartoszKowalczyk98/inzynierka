import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ListenThread extends Thread {
    private int maxClients;
    private int port;
    QuickGUI guiHandle;
    public ListenThread(int maxClients, int port,QuickGUI quickGUI) {
        this.maxClients = maxClients;
        this.port = port;
        this.guiHandle = quickGUI;
    }

    @Override
    public void run() {
        ExecutorService pool = Executors.newFixedThreadPool(maxClients);

        try (var serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(port));
            while (true) {
                pool.execute(new ClientHandlerThread(serverSocket.accept(),guiHandle));
                guiHandle.displayNumberOfClients((((ThreadPoolExecutor) pool).getActiveCount()));
                while ((((ThreadPoolExecutor) pool).getActiveCount()) == maxClients) {
                    new RejectionThread(serverSocket.accept()).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            pool.shutdownNow();

        }
    }
    private class RejectionThread extends Thread{
        Socket socket;
        DataOutputStream dataOutputStream;

        public RejectionThread(Socket socket) throws IOException {
            this.socket = socket;
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                dataOutputStream.writeBytes("Too many clients connected right now!");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

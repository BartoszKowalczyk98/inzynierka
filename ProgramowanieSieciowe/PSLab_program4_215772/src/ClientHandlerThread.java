import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ClientHandlerThread extends Thread {
    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    String messageReceived;
    boolean isConnected;
    QuickGUI guiHandle;

    public ClientHandlerThread(Socket socket, QuickGUI quickGUI) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        isConnected = true;
        guiHandle = quickGUI;
    }

    @Override
    public void run() {
        try {
            guiHandle.displayMessage("połączono z " + socket.getRemoteSocketAddress() + " port " + socket.getLocalPort());
            do {
                messageReceived = "";
                int bytesRead;
                String received;

                do {
                    byte[] bytes = new byte[1024];

                    bytesRead = dataInputStream.read(bytes);
                    if (bytesRead == -1) {
                        isConnected = false;
                        break;
                    }
                    received = new String(bytes, 0, bytesRead);
                    messageReceived += received;

                } while (dataInputStream.available() != 0);

                if (!isConnected) {
                    break;
                }

                guiHandle.displayMessage("Otrzymano " + messageReceived.length() + " byte'ów z adresu: " + socket.getRemoteSocketAddress() +
                        " portu: " + socket.getLocalPort() + "\nWiadomość od Klienta: " + messageReceived);

                dataOutputStream.writeBytes(messageReceived);
                guiHandle.displayMessage("Wysłano " + messageReceived.length() + " byte'ów");
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

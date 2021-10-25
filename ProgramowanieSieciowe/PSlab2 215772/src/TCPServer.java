import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TCPServer {
    static Scanner scanner = new Scanner(System.in);
    private static int port = 7;


    String messageReceived;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    boolean isConnected;

    public TCPServer(Socket socket) {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            isConnected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            port = getPort();
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));

            System.out.println("czekam na klienta");
            Socket connectionsocket = serverSocket.accept();
            do {
                System.out.println("połączono z " + connectionsocket.getRemoteSocketAddress() + " port " + connectionsocket.getLocalPort());
                TCPServer tcpServer = new TCPServer(connectionsocket);

                do {
                    tcpServer.messageReceived = "";
                    int bytesRead;
                    String received;

                    do {
                        byte[] bytes = new byte[1024];

                        bytesRead = tcpServer.dataInputStream.read(bytes);
                        if (bytesRead == -1) {
                            tcpServer.isConnected = false;
                            break;
                        }
                        received = new String(bytes, 0, bytesRead);
                        tcpServer.messageReceived += received;

                    } while (tcpServer.dataInputStream.available() != 0);

                    if (!tcpServer.isConnected) {
                        break;
                    }

                    System.out.println("Otrzymano " + tcpServer.messageReceived.length() + " byte'ów z adresu: " + connectionsocket.getInetAddress() + " portu: " + connectionsocket.getLocalPort());
                    System.out.println("Wiadomość od Klienta: " + tcpServer.messageReceived);

                    tcpServer.dataOutputStream.writeBytes(tcpServer.messageReceived);
                    System.out.println("Wysłano " + tcpServer.messageReceived.length() + " byte'ów");

                } while (true);
                if (!tcpServer.isConnected) {
                    clearScreen();
                    System.out.println("Klient się rozłączył");
                    connectionsocket.close();
                    System.out.println("Czekam na kolejnego klienta");
                    connectionsocket = serverSocket.accept();
                }
            } while (connectionsocket.isConnected());
            serverSocket.close();
        } catch (IOException |
                IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (
                InputMismatchException e) {
            System.out.println("Wpisałeś błędne dane!");
        }

    }

    //pobieranie nr portu
    private static int getPort() throws InputMismatchException {
        System.out.println("Podaj nr portu do połączenia: ");
        int port = scanner.nextInt();
        scanner.nextLine();
        return port;
    }
    public static void clearScreen() {
        for(int clear = 0; clear < 50; clear++)
        {
            System.out.println("\b") ;
        }
    }

}

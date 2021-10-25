import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

class TCPClient {
    static Scanner scanner = new Scanner(System.in);

    String messageToSend;
    String messageReceived;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public TCPClient(Socket socket) {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {

            int port = getPort();
            Socket socket = new Socket();
            InetAddress inetAddress = InetAddress.getByName("localhost");
            SocketAddress socketAddress =new InetSocketAddress(inetAddress,port);
            socket.connect(socketAddress);

            TCPClient tcpClient = new TCPClient(socket);
            System.out.println("Podaj wiadomość do serwera, wpisanie \"koniec\" zamyka socket i przerywa działanie programu ");
            do {
                tcpClient.messageReceived = "";
                tcpClient.messageToSend = scanner.nextLine();
                if(tcpClient.messageToSend.equals("koniec"))
                    break;

                tcpClient.dataOutputStream.writeBytes(tcpClient.messageToSend);
                System.out.println("Wysłano "+ tcpClient.messageToSend.length()+" byte'ów");

                int bytesRead;
                String temp;
                do{
                    byte[] bytes = new byte[1024];
                    bytesRead = tcpClient.dataInputStream.read(bytes);
                    temp = new String(bytes, 0,bytesRead);
                    tcpClient.messageReceived+=temp;
                }while (tcpClient.dataInputStream.available()!=0);
                System.out.println("Otrzymano "+tcpClient.messageReceived.length()+" byte'ów");
                System.out.println("Wiadomość od serwera: " + tcpClient.messageReceived);

            } while (!tcpClient.messageToSend.equals("koniec"));
            socket.close();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
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
}
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServerBroadcast {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(getPort());
            byte [] bytes = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
            String received;
            do{
                datagramSocket.receive(datagramPacket);
                received = new String(datagramPacket.getData());
                System.out.printf("%s",received);
                System.out.println("z adresu "+datagramPacket.getAddress()+" przez port "+ datagramPacket.getPort()+"\n");
            }while (!received.equals("koniec"));
            datagramSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

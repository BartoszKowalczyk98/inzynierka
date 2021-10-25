import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Server {
    static Scanner scanner = new Scanner(System.in);
    static int port;


    public static void main(String[] args) {
        port = getPort();
        try {
            byte[] arr = new byte[1024];
            DatagramSocket datagramSocket = new DatagramSocket(port);
            String received;

            do {
                DatagramPacket packet = new DatagramPacket(arr, arr.length);
                datagramSocket.receive(packet);
                InetAddress address = packet.getAddress();
                int port2 = packet.getPort();
                packet = new DatagramPacket(arr, arr.length, address, port2);
                received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Server otrzymał: " + received);
                System.out.println("Od adresu: "+packet.getAddress()+" przez port: "+packet.getPort());
                datagramSocket.send(packet);
            } while (!received.equals("close"));

            datagramSocket.close();

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

import java.io.IOException;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    static Scanner scanner = new Scanner(System.in);
    static int port;

    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            port = getPort();
            String toBeSent;
            do {
                System.out.println("podaj co przesłać do serwera (podanie \"close\" zakonczy wysylanie): ");
                toBeSent = scanner.nextLine();
                byte[] arr = toBeSent.getBytes();

                DatagramPacket packet = new DatagramPacket(arr, arr.length, address, port);
                datagramSocket.send(packet);
                packet = new DatagramPacket(arr, arr.length);
                datagramSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Klient otrzymał: " + received);
            } while (!toBeSent.equals("close"));
            datagramSocket.close();
        } catch (SocketException | UnknownHostException e) {
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

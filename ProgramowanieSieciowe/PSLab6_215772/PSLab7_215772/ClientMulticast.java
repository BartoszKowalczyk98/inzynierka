import java.io.IOException;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientMulticast {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            int port = getPort();
            InetAddress grupa = InetAddress.getByName(getGroupAddress());

            byte[] bytes;
            do {
                System.out.println("Podaj co chesz rozeslac (podanie \"koniec\" zakonczy wysylanie");
                bytes = scanner.nextLine().getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, grupa, port);
                socket.send(packet);
            } while (!new String(bytes).equals("koniec"));
            socket.close();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //pobieranie adresu grupy
    private static String getGroupAddress() throws InputMismatchException {
        System.out.println("Podaj adres ip grupy do multicastu: ");
        return scanner.nextLine();
    }

    //pobieranie nr portu
    private static int getPort() throws InputMismatchException {
        System.out.println("Podaj nr portu do połączenia: ");
        int port = scanner.nextInt();
        scanner.nextLine();
        return port;
    }
}

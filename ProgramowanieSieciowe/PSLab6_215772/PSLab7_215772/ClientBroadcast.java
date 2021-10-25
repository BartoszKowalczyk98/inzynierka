import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientBroadcast {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress inetAddress = getLocalHostLANAddress();
            socket.setBroadcast(true);
            int port = getPort();
            byte[] bytes;
            do {
                System.out.println("Podaj co chcesz wysłać jako broadcast(wpisanie \"koniec\" zakonczy wysylanie)");
                bytes = scanner.nextLine().getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, port);
                socket.send(datagramPacket);
                System.out.println("wysłano " + datagramPacket.getLength() + "bajtow danych jako broadcast");
            } while (!new String(bytes).equals("koniec"));
            socket.close();
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

    private static InetAddress getLocalHostLANAddress() throws UnknownHostException {
        try {
            InetAddress inetAddress = null;
            for (Enumeration interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();

                for (Enumeration iNetAddresses = networkInterface.getInetAddresses(); iNetAddresses.hasMoreElements(); ) {
                    InetAddress inetAddress1 = (InetAddress) iNetAddresses.nextElement();
                    if (!inetAddress1.isLoopbackAddress()) {
                        if (inetAddress1.isSiteLocalAddress()) {
                            return inetAddress1;
                        } else if (inetAddress == null) {
                            inetAddress = inetAddress1;
                        }
                    }
                }
            }
            //znaleziony adress
            if (inetAddress != null)
                return inetAddress;

            // nie znalezlismy adresu
            InetAddress jdksuppliedAddress = InetAddress.getLocalHost();
            if (jdksuppliedAddress == null)
                throw new UnknownHostException();
            return jdksuppliedAddress;


        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}

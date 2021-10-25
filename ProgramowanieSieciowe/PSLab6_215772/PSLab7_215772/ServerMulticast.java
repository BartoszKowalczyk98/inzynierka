import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ServerMulticast {
    private static final Scanner scanner = new Scanner(System.in);
    //adres grupy
    //230.0.0.0
    public static void main(String[] args) {
        try {
            MulticastSocket socket = new MulticastSocket(getPort());
            InetAddress grupa = InetAddress.getByName(getGroupAddress());
            socket.joinGroup(grupa);
            byte[] bytes = new byte[1024];
            while (true){
                DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
                socket.receive(packet);
                String received = new String(packet.getData(),0,packet.getLength());
                System.out.printf("Otrzymano: %s", received);
                System.out.printf("z adresu "+packet.getAddress()+" przez port "+packet.getPort()+"\n\n");
                if("koniec".equals(received)){
                    break;
                }
            }
            socket.leaveGroup(grupa);
            socket.close();
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

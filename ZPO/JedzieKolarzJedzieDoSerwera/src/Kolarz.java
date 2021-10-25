import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Kolarz {
    String serverAddress;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Random random = new Random();
    String route = "S                   |       |         |                |           |      M";
    String bike = "o&o";
    String name;
    public Kolarz() {
        this.serverAddress = "127.0.0.1";
        try {
            socket = new Socket(serverAddress, 59001);
            dataInputStream=new DataInputStream(socket.getInputStream());
            dataOutputStream=new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ioio){
            ioio.printStackTrace();
        }
    }

    public static void main(String [] args){
        Kolarz kolarz = new Kolarz();
        try {
            if(kolarz.dataInputStream.readUTF().equals("Start")) {
                kolarz.dataOutputStream.writeUTF("name");
                kolarz.name = kolarz.dataInputStream.readUTF();
                kolarz.symulacja();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void symulacja(){
        System.out.println(name);
        System.out.println(route);
        System.out.printf(bike);
        int ktorylotny = 0;
        try {
            while (true) {
                Thread.sleep((random.nextInt(13) + 3) * 10);
                bike = "." + bike;
                System.out.printf("\r" + bike);

                if (route.charAt(bike.length() - 1) == 'M') {
                    dataOutputStream.writeUTF("koniec");
                    break;
                } else if (route.charAt(bike.length() - 1) == '|') {
                    dataOutputStream.writeUTF("lotny " + (++ktorylotny));
                }
            }
            String komunikat = dataInputStream.readUTF();
            System.out.println("\n"+name + "- nr "+ komunikat + " na mecie");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

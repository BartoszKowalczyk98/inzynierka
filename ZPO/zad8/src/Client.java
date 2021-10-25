import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import paczka8.*;
public class Client {
    public static void main(String[] args) throws Exception {
        try {
            Socket socket = new Socket("127.0.0.1", 59898);
            System.out.println("polaczono z serwerem: ");

            Scanner scanner = new Scanner(System.in);
            Thread wyslanie = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    ObjectOutputStream oosk = new ObjectOutputStream(socket.getOutputStream());

                    Date czas;
                    while (true) {
                            try {
                                System.out.println("podaj tresc notyfikacji");
                                String notka = scanner.nextLine();
                                System.out.println("podaj date odeslania w formacie yyyy-mm-dd hh:mm:ss");
                                String dataodeslania = scanner.nextLine();
                                czas = dateFormat.parse(dataodeslania);
                                Notyfikacja dowyslania = new Notyfikacja(czas, notka);
                                dowyslania.compareDate(new Date());
                                oosk.writeObject(dowyslania);
                                oosk.flush();
                            }
                            catch(DatowyException dex) {
                                System.out.println(dex.GetWarning());
                            }
                            catch (ParseException parex){
                                System.out.println("blad parsowania w wysylaniu zle podales date! ");
                            }
                    }
                    }
                    catch(IOException ioe){
                        System.out.println("IOException error w wysylaniu");
                        ioe.printStackTrace();
                    }

                }
            });

            Thread odbieranie = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream oisk = new ObjectInputStream(socket.getInputStream());

                        while(true) {
                            Notyfikacja dowyswietlenia = (Notyfikacja) oisk.readObject();
                            System.out.println(dowyswietlenia.getTekst() + " data: " + dowyswietlenia.data);
                        }
                    }
                    catch (ClassNotFoundException cnfex) {
                        cnfex.printStackTrace();
                    }

                    catch(IOException ioex)
                    {
                        ioex.printStackTrace();
                    }
                    catch (Exception e) {
                        System.out.println("nie wiem co sie staneło! ");
                    }
                }
            });
            wyslanie.start();
            odbieranie.start();

        }
        catch(Exception e){
            System.out.println("cos sie stalo w kliencie i nie bardzo wiem co");
        }
    }
}
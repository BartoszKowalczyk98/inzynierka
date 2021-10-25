import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Main {
    public static int iloscosob = 100;
    public static double prawdopodobienstwo1 = 0.05;
    public static double prawdopodobienstwo2 = 0.3;
    public static int minliczbaosob = 5;
    public static int ilesekundtrwafilm = 4;

    public static void main(String[] args) {
        ExecutorService poolprzedfilmem = Executors.newFixedThreadPool(iloscosob);
        List<Osoba> osobaList = new ArrayList<>();
        List<Widz> widzList = new ArrayList<>();

        for (int i = 0; i < iloscosob; i++) {
            osobaList.add(new Osoba(prawdopodobienstwo1));
        }
        try {

            poolprzedfilmem.invokeAll(osobaList);
            poolprzedfilmem.shutdown();
            while (!poolprzedfilmem.isTerminated()) {
            }
            ;
            int ileosob = 0;
            for (Osoba o : osobaList) {
                if (o.ogladamfilm) {
                    ileosob++;
                    widzList.add(new Widz(prawdopodobienstwo2, ilesekundtrwafilm));
                }
            }
            if (ileosob < minliczbaosob) {
                System.out.println("przepraszamy, filmu nie bedzie! Ilosc osbo ktore sie zadeklarowaly: " + ileosob);
                return;
            }
            else{
                System.out.println("na film przyszlo "+ileosob+" osob");
            }
            ExecutorService poolnafilm = Executors.newFixedThreadPool(widzList.size());
            poolnafilm.invokeAll(widzList);

            int ileosobjeszczeoglada = 0;
            for(Widz w : widzList){
                if(w.zostajeogladac){
                    ileosobjeszczeoglada++;
                }
            }
            poolnafilm.shutdown();
            if (ileosobjeszczeoglada >= 5) {
                System.out.println("film dajlej trwa");
                Thread.sleep((ilesekundtrwafilm / 2) * 100);
                System.out.println("film sie skonczyl ostatecznie ogladalo do konca: " + ileosobjeszczeoglada);
                return;
            } else {
                System.out.println("Klienci frajerzy nie dostana zwrotu po co wogole przychodzili ile osob by faktycznie ogladalo " + ileosobjeszczeoglada);
                return;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

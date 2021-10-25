import java.io.*;
import java.util.LinkedHashSet;

public class Main {

    static String sciezka = new File("").getAbsolutePath() + "\\src\\";
    static String nazwapliku1 = "gen1.dat";
    static String nazwapliku2 = "gen2.dat";
    static String nazwapliku3 = "gen3.dat";

    static int rozmiar = 1000;

    public static void main(String[] args) {
        funkcjasprawdzajaca(sciezka + nazwapliku1, rozmiar);
        funkcjasprawdzajaca(sciezka + nazwapliku2, rozmiar);
        funkcjasprawdzajaca(sciezka + nazwapliku3, rozmiar);
    }

    private static void funkcjasprawdzajaca(String filePath, int maxSize) {

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

        int i;
        int indeks = 0;

        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(new File(filePath)));

            while (true) {
                i = dataInputStream.readInt();

                if (linkedHashSet.contains(i)) {
                    int gdziebadseq = getIndex(linkedHashSet, i);
                    int temp = linkedHashSet.size();

                    System.out.println("BAD SEQ: " + (indeks + gdziebadseq) + ", " + (indeks + temp) + ", period length = " + (temp - gdziebadseq));
                    dataInputStream.close();
                    break;
                }

                if (maxSize == linkedHashSet.size()) {
                    linkedHashSet.remove(linkedHashSet.iterator().next());
                    indeks++;
                }

                linkedHashSet.add(i);

                if (dataInputStream.available() == 0) {
                    dataInputStream.close();
                    System.out.println("GOOD SEQ");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getIndex(LinkedHashSet<Integer> linkedHashSet, int liczba) {
        int wynik = 0;
        for (Integer i : linkedHashSet) {
            if (i.equals(liczba))
                return wynik;

            wynik++;
        }
        return -1;
    }
}

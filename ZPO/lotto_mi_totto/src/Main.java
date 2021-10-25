import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.management.StandardEmitterMBean;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    static LocalDate firstLotto = LocalDate.of(1957, 1, 27);

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String url = "http://megalotto.pl/wyniki/lotto/losowania-z-roku-";


        String string; //= scan.nextLine();
        String[] helper; //= string.split("-");
        String urlhelp;

        System.out.println("Podaj co chcesz otrzymać:\n Wyniki losowania z konkretnego dnia - 1\n histogram wylosowanych liczb w zadanym roku - 2\n histogram " +
                "liczb w zadanym przedzialeczasowym - 3");
        int wybor = Integer.parseInt(scan.nextLine());


        if (wybor == 1) {
            //wyniki z konkretnego dnia
            try {
                System.out.println("Podaj date (dd-mm-yyyy)");
                string = scan.nextLine();
                helper = string.split("-");
                urlhelp = url + helper[2];
                LocalDate data = LocalDate.of(Integer.parseInt(helper[2]), Integer.parseInt(helper[1]), Integer.parseInt(helper[0]));
                List<Integer> liczby = getResultFromDate(data, urlhelp);
                System.out.println(liczby);
            } catch (IncorrectDateException e) {
                System.out.println("Podana data jest nieprawidlowa.");
                e.printStackTrace();
            }
        }
        else if(wybor == 2) {
            //histogram roku
            try {
                System.out.println("Podaj rok");
                int rok = scan.nextInt();
                urlhelp = url + rok;
                HashMap<Integer, Integer> dict = getHistogramFromYear(rok, urlhelp);
                for (int i = 1; i < 50; i++) {
                    assert dict != null;
                    System.out.println(i+ "-".repeat(dict.get(i))+dict.get(i));
                }
            } catch (IncorrectDateException e) {
                System.out.println("Podana data jest nieprawidlowa.");
                e.printStackTrace();
            }
        }
        else if(wybor ==3) {
            //histogram od do
            try {
                url = "http://megalotto.pl/wyniki/lotto/losowania-od-";
                System.out.println("Podaj poczatek przedzialu (dd-mm-yyyy): ");
                string = scan.nextLine();
                helper = string.split("-");
                LocalDate begin = LocalDate.of(Integer.parseInt(helper[2]), Integer.parseInt(helper[1]), Integer.parseInt(helper[0]));

                System.out.println("Podaj koniec przedzialu (dd-mm-yyyy)");
                string = scan.nextLine();
                helper = string.split("-");
                LocalDate end = LocalDate.of(Integer.parseInt(helper[2]), Integer.parseInt(helper[1]), Integer.parseInt(helper[0]));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-YYYY");
                String beginString = formatter.format(begin);
                int index = beginString.indexOf("-")+1;//indeks 1 litery miesiaca zeby byla wielka
                beginString = beginString.substring(0,index) + Character.toUpperCase(beginString.charAt(index))+beginString.substring(index+1);

                String endString = formatter.format(end);
                index = endString.indexOf("-") +1;
                endString = endString.substring(0,index)+ Character.toUpperCase(endString.charAt(index))+ endString.substring(index+1);
                url +=  beginString + "-do-" + endString;

                HashMap<Integer, Integer> hist = getHistogramBetweenDates(begin, end, url);
                for (int i = 1; i < 50; i++) {
                    assert hist != null;
                    System.out.println(i+ "-".repeat(hist.get(i))+hist.get(i));

                }

            } catch (IncorrectDateException e) {
                System.out.println("Bledne dane wejsciowe!");
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Podales bledne dane");
        }
    }

    static Element findElement(Elements elements, String date) {
        boolean found = false;
        Element result = null;
        for (Element e : elements) {
            Elements children = e.children();
            if(children.get(1).childNode(0).toString().equals(date)){
                result = e;
                found = true;
            }
            if (found)
                break;
        }

        return result;
    }

    static List<Integer> getResultFromDate(LocalDate date, String url) throws IncorrectDateException {
        List<Integer> wynik = new ArrayList<>();
        String data = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByAttributeValue("style", "position: relative;");
            Element element = findElement(elements, data);
            if (element == null)
                throw new IncorrectDateException();
            for (int i = 2; i < 8; i++) {
                int liczba = Integer.parseInt(element.child(i).childNode(0).toString().trim());
                wynik.add(liczba);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wynik;
    }

    static HashMap<Integer, Integer> getHistogramFromYear(int year, String url) throws IncorrectDateException {
        if (year < firstLotto.getYear() || Year.of(year).isAfter(Year.now()))
            throw new IncorrectDateException();
        return getHashMap(url);
    }

    static HashMap<Integer, Integer> getHistogramBetweenDates(LocalDate startDate, LocalDate endDate, String url) throws IncorrectDateException {
        if (startDate.isBefore(firstLotto) || endDate.isAfter(LocalDate.now()) || startDate.isAfter(endDate))
            throw new IncorrectDateException();
        return getHashMap(url);
    }

    private static HashMap<Integer, Integer> getHashMap(String url) {
        try {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByAttributeValue("style", "position: relative;");
            for (Element e : elements) {
                for (int i = 2; i < 8; i++) {
                    int liczba = Integer.parseInt(e.child(i).childNode(0).toString().trim());
                    hashMap.merge(liczba, 1, Integer::sum);
                }
            }
            return hashMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
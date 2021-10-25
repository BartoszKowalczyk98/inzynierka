package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main {

    // data i zabawy z czasem

    // ile dni trwała II wojna światowa (wliczając zarówno 1.09.1939, jak i 8.05.1945);
    public static void wojnaswiatowa(){
        try {
            String time1 = "31.10.1939";
            String time2 = "09.05.1945";

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            long difference = date2.getTime() - date1.getTime();

            System.out.println("dni wojny swiatowej: "+ TimeUnit.DAYS.convert(difference,TimeUnit.MILLISECONDS));

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
      //którego dnia i miesiąca wypada 68-my dzień roku 2016;
    public static void przesunieciedni(int ile){
        try {
            String time0 = "01.01.2016";
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date0 = format.parse(time0);
            int ilednipozniej=ile;
            long nowyczas = date0.getTime() +( ilednipozniej * 24*60*60*1000);
            date0 = new Date(nowyczas);
            System.out.println(date0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // ile razy suma cyfr liczby godzin i minut, w formacie 24-godzinnym hh:mm,
    //wynosiła dokładnie 15, jeśli ograniczamy się do przedziału czasowego od 11:45
    //rano do 22:30 wieczorem (przykłady: 17:52, 21:39); napisz testy;
    public static void ile15() {
        try {
            String starttime = "11:45";
            String endtime = "22:30";
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            Date startDate = format.parse(starttime);
            Date endDate = format.parse(endtime);
            int licznikpietnastek = 0;
            while (!startDate.equals(endDate)) {
                if (sumOfDate(startDate) == 15)
                    licznikpietnastek++;
                startDate.setTime(startDate.getTime() + 60000);
            }
            System.out.println(licznikpietnastek);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //ile razy 29 luty od moich urodzin
    public static void ileprzestepnychlat(int rok,int miesiac,int dzien){
        int iletychlutych=0;
        LocalDate obecny = LocalDate.now();
        LocalDate urodziny = LocalDate.of(rok,miesiac,dzien);
        if(urodziny.isLeapYear()){
            if(urodziny.isBefore(LocalDate.of(urodziny.getYear(),29,2)))
                iletychlutych++;
            urodziny = urodziny.plusYears(4);
        }
        while (urodziny.isBefore(obecny)){
            if(urodziny.isLeapYear()){
                iletychlutych++;
            }
            urodziny= urodziny.plusYears(1);
        }

        if(obecny.isLeapYear()){
            if(obecny.isAfter(LocalDate.of(obecny.getYear(),2,29))) {
                iletychlutych++;
            }
        }
        System.out.println("tyle tych lutych "+iletychlutych);
    }
    public static void main(String[] args) {
        wojnaswiatowa();
        przesunieciedni(67);
        ile15();
        ileprzestepnychlat(1998,8,26);
    }
    private static int sumOfDate(Date date){
        return getSum(date.getHours()) + getSum(Integer.valueOf(date.getMinutes()));
    }
    static int getSum(int n)
    {
        int sum = 0;
        while (n != 0)
        {
            sum = sum + n % 10;
            n = n/10;
        }
        return sum;
    }
}

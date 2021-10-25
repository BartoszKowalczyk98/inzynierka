import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static int N = 1000000000;

    public static void main(String[] args) {
        byte[] arr = new byte[N];

        for (int i = 0; i < N; i++) arr[i] = 1;
        long temp = System.currentTimeMillis();
        odpalanieSita(arr, 4);
        temp = System.currentTimeMillis() - temp;
        System.out.println(temp / 1000);
        int iletychprimow =0;
        for(int i = 2 ;i<N;i++){
            if(arr[i]==1)
                iletychprimow++;
            if(i==1000000)
                System.out.println(iletychprimow);
            if(i==10000000)
                System.out.println(iletychprimow);
            if(i==100000000)
                System.out.println(iletychprimow);
        }
        System.out.println(iletychprimow);

    }

    private static void odpalanieSita(byte[] arr, int ilewatkow) {
        List<Wykreslaczka> wykreslaczkaList = new CopyOnWriteArrayList<>();
        int wheretostart = 2;
        for (int i = 2; i * i < N;i++ ) {
            for (int j = i; j < N; j++) {
                if (arr[j] == 1) {
                    wheretostart = j;
                    break;
                }
            }
            i = wheretostart;
            synchronized (wykreslaczkaList) {
                while (wykreslaczkaList.size() == ilewatkow - 1) {
                    for (Wykreslaczka w : wykreslaczkaList) {
                        if (w.getState().equals(Thread.State.TERMINATED))
                            wykreslaczkaList.remove(w);
                    }
                }
                Wykreslaczka temp = new Wykreslaczka(wheretostart, N, arr);
                wykreslaczkaList.add(temp);

                temp.start();
            }

        }

    }

    private static class Wykreslaczka extends Thread {
        int pocz;
        int max;
        byte[] liczby;

        public Wykreslaczka(int pocz, int max, byte[] liczby) {
            this.pocz = pocz;
            this.max = max;
            this.liczby = liczby;
        }

        @Override
        public void run() {
            int i = pocz;
            while ((i * pocz) < max) {
                liczby[i * pocz] = 0;
                i++;
            }
        }
    }
}

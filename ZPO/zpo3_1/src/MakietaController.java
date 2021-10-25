import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.scene.control.Label;

public class MakietaController {
    @FXML
    private Label mylabel;

    public MakietaController() {
    }

    @FXML
    private void initialize() {
        LabelUpdater labelUpdater = new LabelUpdater();
        Thread thread = new Thread(labelUpdater);
        thread.start();
    }


    public class LabelUpdater implements Runnable {
        /**
         * stale ale moge zmeinic :)
         */
        int ile_Wyswietlac = 4;
        int ilu_Kolarzy =12;
        int opoznienie = (1 / 25) * 1000;

        boolean koniec_Wyscigu = false;
        String coWyswietlic;
        KolarzTask kto_Ostatnio_Skonczyl;
        PriorityQueue<KolarzTask> priorityQueue = new PriorityQueue<>();

        public LabelUpdater() {
            coWyswietlic = "";
        }

        public void run() {
            try {

                //logger
                Logger logger = Logger.getLogger("loger");
                FileHandler fileHandler = new FileHandler("C:\\Users\\kowal\\IdeaProjects\\zpo3_1\\loger.log");
                logger.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                logger.info("stworzenie Loggera");

                HashSet<String> nazwiska = new URLReader("http://szgrabowski.kis.p.lodz.pl/zpo19/nazwiska.txt").readFromURL();
                ArrayList<KolarzTask> kolarzTasks = new ArrayList<>();

                for (Iterator<String> nazwisko = nazwiska.iterator(); nazwisko.hasNext(); ) {
                    kolarzTasks.add(new KolarzTask(nazwisko.next(),logger));
                }


                for (int i = 0; i < ilu_Kolarzy; i++)
                    new Timer().schedule(kolarzTasks.get(i), opoznienie * i);

                synchronized (kolarzTasks) {
                    while (!koniec_Wyscigu) {
                        for (KolarzTask k : kolarzTasks) {
                            if (k.koniec) {
                                if (!priorityQueue.contains(k)) {
                                    priorityQueue.add(k);
                                    kto_Ostatnio_Skonczyl = k;
                                }
                            }
                        }
                        PriorityQueue<KolarzTask> tempPriorityQueue = new PriorityQueue<>(priorityQueue);
                        synchronized (priorityQueue) {
                            for (int i = 0; i < 4 && i < tempPriorityQueue.size(); i++) {
                                KolarzTask temp = tempPriorityQueue.poll();
                                coWyswietlic = coWyswietlic + "miejsce " + (i + 1) + "  " + temp.nazwisko + "    " + temp.czas + "\n\n";
                            }
                        }
                        if (kto_Ostatnio_Skonczyl != null) {
                            coWyswietlic += "wyscig ukonczyl " + kto_Ostatnio_Skonczyl.nazwisko + " z czasem " + kto_Ostatnio_Skonczyl.czas + "\n\n";
                        }

                        Platform.runLater(() -> {
                            mylabel.setText(coWyswietlic);
                            coWyswietlic = "";
                        });
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (priorityQueue.size() == ilu_Kolarzy) {
                            /*for (KolarzTask k : priorityQueue) { tylko bylo potrzebne do sprawdzania danych
                                System.out.println(k.nazwisko + "  " + k.czas);
                                //System.out.println("koniec wyscigu");
                            }*/
                            koniec_Wyscigu = true;
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("sth wrong with urlreader");
            }
        }
    }
}

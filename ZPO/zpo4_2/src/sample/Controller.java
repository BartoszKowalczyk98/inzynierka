package sample;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Controller {
    @FXML
    private Label slowoDoPrzetlumaczenia;
    @FXML
    private Button start;
    @FXML
    private TextArea wpisywanie;
    //zmienne do przetrzymywania danych
    ArrayList<Slowo> slowa = deserialozowanie();
    Slowo aktualneSlowo;
    long czasRozpoczecia;
    double punkty;
    int nrpytania = 1;
    ArrayList<Odpowiedz> odpowiedzi= new ArrayList<>();
    Random random = new Random();

    @FXML
    private void handleEnter(KeyEvent event) {
        if (wpisywanie.isEditable()) {
            KeyCode key = event.getCode();
            if (key == KeyCode.ENTER) {
                String wprowadzoneSlowo = wpisywanie.getText();
                wprowadzoneSlowo = wprowadzoneSlowo.substring(0, wprowadzoneSlowo.length() - 1);
                double odlegloscLevensteina = Levenstein.minLevQWERTY(wprowadzoneSlowo, aktualneSlowo.poAngielsku);
                if (odlegloscLevensteina == 0) {
                    punkty += 1;
                } else if (odlegloscLevensteina == 1) {
                    punkty += 0.5;
                }
                odpowiedzi.add(new Odpowiedz(aktualneSlowo.poPolsku, wprowadzoneSlowo));
                aktualneSlowo = slowa.remove(random.nextInt(slowa.size()));
                nrpytania++;
                if (nrpytania == 6) {
                    try {
                        long koncowyczas =System.currentTimeMillis();
                        double czasWSekundach = (double)(koncowyczas-czasRozpoczecia)/10.0;
                        Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
                        String jsonarr = gson.toJson(odpowiedzi);
                        FileWriter fileWriter = new FileWriter("C:\\Users\\Bartosz\\IdeaProjects\\zpo4_2\\src\\sample\\imie_nazwisko.json");
                        gson.toJson(jsonarr, fileWriter);
                        fileWriter.close();
                        slowoDoPrzetlumaczenia.setText("gratulacje koniec testu!\nTwoj wynik to "+punkty+"\nTest ukonczyles/as w czasie "+Math.floor(czasWSekundach)/100.0);
                        wpisywanie.setEditable(false);
                        wpisywanie.setText(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    slowoDoPrzetlumaczenia.setText(aktualneSlowo.poPolsku);
                    wpisywanie.setText(null);
                }
            }
        }
    }

    @FXML
    private void handleStart() {
        aktualneSlowo = slowa.remove(random.nextInt(slowa.size()));
        slowoDoPrzetlumaczenia.setText(aktualneSlowo.poPolsku);
        wpisywanie.setEditable(true);
        czasRozpoczecia = System.currentTimeMillis();
        start.setDisable(true);
    }

    public static ArrayList<Slowo> deserialozowanie() {
        ArrayList<Slowo> wynik = new ArrayList<>();
        com.google.gson.Gson gson = new com.google.gson.GsonBuilder().create();
        Path path = new File("C:\\Users\\Bartosz\\IdeaProjects\\zpo4_2\\src\\sample\\PolEngTest.json").toPath();
        try {
            Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            Slowo[] tablicaSlow = gson.fromJson(reader, Slowo[].class);
            Collections.addAll(wynik, tablicaSlow);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wynik;
    }

}

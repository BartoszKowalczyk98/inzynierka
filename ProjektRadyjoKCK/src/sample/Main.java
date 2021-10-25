package sample;

import Knob.Knob;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {
    Media radio1 = new Media(new File("C:\\Users\\kowal\\IdeaProjects\\ProjektKCKgit\\src\\muzyka\\radio1.mp3").toURI().toString());
    MediaPlayer mediaPlayerRadio1 = new MediaPlayer(radio1);
    Media radio2 = new Media(new File("C:\\Users\\kowal\\IdeaProjects\\ProjektKCKgit\\src\\muzyka\\radio2.mp3").toURI().toString());
    MediaPlayer mediaPlayerRadio2 = new MediaPlayer(radio2);
    Media radiozaklocenia = new Media(new File("C:\\Users\\kowal\\IdeaProjects\\ProjektKCKgit\\src\\muzyka\\radiozaklocenia.mp3").toURI().toString());
    MediaPlayer mediaPlayerRadiozaklocenia = new MediaPlayer(radiozaklocenia );
    MediaPlayer aktualneradio = mediaPlayerRadiozaklocenia;


    Label wyswietlanievolume;
    Label wyswietlaniegodziny;
    Label wyswietlanieczestotliwosc;
    ImageView glosnik;

    Image glosnikdziala = new Image(new File("C:\\Users\\kowal\\IdeaProjects\\ProjektKCKgit\\src\\sample\\glosnik.gif").toURI().toString());
    Image glosnikniedziala = new Image(new File("C:\\Users\\kowal\\IdeaProjects\\ProjektKCKgit\\src\\sample\\glosnikstoi.gif").toURI().toString());

    boolean czy_radio_wlaczone = false;

    Knob knob;
    Knob knob2;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("odtwarzacz nuzyki");


        ////////////////////////////przycisken
        Button button = new Button();
        button.setId("play");
        button.setMinSize(30, 30);
        button.setTranslateX(23);
        button.setTranslateY(30);

        mediaPlayerRadio1.play();
        mediaPlayerRadio1.setVolume(0);
        mediaPlayerRadio2.play();
        mediaPlayerRadio2.setVolume(0);
        mediaPlayerRadiozaklocenia.play();
        mediaPlayerRadiozaklocenia.setVolume(0);


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!czy_radio_wlaczone) {
                    czy_radio_wlaczone = true;
                    aktualneradio.setVolume((knob.getValue() * 2.0) / 100.0);
                    glosnik.setImage(glosnikdziala);
                    button.setStyle("" +
                            "-fx-background-image: url(\"sample/buttonon.PNG\");\n" +
                            "-fx-background-repeat: stretch;\n" +
                            "   -fx-background-size: 30 30;" +
                            "");
                } else {
                    czy_radio_wlaczone = false;
                    aktualneradio.setVolume(0);
                    glosnik.setImage(glosnikniedziala);
                    button.setStyle("" +
                            "-fx-background-image: url(\"sample/buttonoff.PNG\");\n" +
                            "-fx-background-repeat: stretch;\n" +
                            "   -fx-background-size: 30 30;" +
                            ""); }
            }
        });

        wyswietlanievolume = new Label();
        wyswietlanievolume.setId("wyswietlacz");
        wyswietlanievolume.setTranslateX(124);
        wyswietlanievolume.setTranslateY(-90);
        wyswietlanievolume.setWrapText(true);
        wyswietlanievolume.setPrefHeight(100);
        wyswietlanievolume.setPrefWidth(150);

        wyswietlaniegodziny = new Label();
        wyswietlaniegodziny.setId("wyswietlacz");
        wyswietlaniegodziny.setTranslateX(124);
        wyswietlaniegodziny.setTranslateY(-111);
        wyswietlaniegodziny.setWrapText(true);
        wyswietlaniegodziny.setPrefHeight(100);
        wyswietlaniegodziny.setPrefWidth(150);

        wyswietlanieczestotliwosc = new Label();
        wyswietlanieczestotliwosc.setId("wyswietlacz");
        wyswietlanieczestotliwosc.setTranslateX(124);
        wyswietlanieczestotliwosc.setTranslateY(-66);
        wyswietlanieczestotliwosc.setWrapText(true);
        wyswietlanieczestotliwosc.setPrefHeight(100);
        wyswietlanieczestotliwosc.setPrefWidth(150);


        glosnik = new ImageView();
        glosnik.setImage(glosnikniedziala);
        glosnik.setTranslateX(-145);
        glosnik.setTranslateY(0);


        ////////////////////////////////////////////////////////////////////////////////////poczatek pokretel
        knob = new Knob();
        knob.setValue(25);
        knob.setTranslateX(35);
        knob.setTranslateY(115);
        knob.setPrefWidth(105);
        knob.setPrefHeight(105);
        knob.setMaxHeight(105);
        knob.setMaxWidth(105);


        double[] dragDelta = new double[2];
        double[] dragHelp = new double[2];

        knob.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta[0] = mouseEvent.getScreenX();
                dragDelta[1] = mouseEvent.getScreenY();
                //System.out.println(dragDelta[0] + " " + dragDelta[1]);
            }
        });

        knob.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dragHelp[0] = mouseEvent.getScreenX() - dragDelta[0];
                dragHelp[1] = mouseEvent.getScreenY() - dragDelta[1];
                if (dragHelp[0] > 0 && knob.getValue() < knob.maxValueProperty().doubleValue()) {
                    knob.setValue(knob.getValue() + 1);
                    wyswietlanievolume.setText("Volume: " + knob.getValue());
                } else if (dragHelp[0] < 0 && knob.getValue() > knob.minValueProperty().doubleValue()) {
                    knob.setValue(knob.getValue() - 1);
                    wyswietlanievolume.setText("Volume: " + knob.getValue());
                }
                if (czy_radio_wlaczone) aktualneradio.setVolume((knob.getValue() * 2.0) / 100.0);
            }
        });

        knob2 = new Knob(88, 103);
        knob2.setValue(90);
        knob2.setTranslateX(205);
        knob2.setTranslateY(115);
        knob2.setPrefWidth(105);
        knob2.setPrefHeight(105);
        knob2.setMaxHeight(105);
        knob2.setMaxWidth(105);

        knob2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta[0] = mouseEvent.getScreenX();
                dragDelta[1] = mouseEvent.getScreenY();
            }
        });

        knob2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dragHelp[0] = mouseEvent.getScreenX() - dragDelta[0];
                dragHelp[1] = mouseEvent.getScreenY() - dragDelta[1];
                if (dragHelp[0] > 0 && knob2.getValue() < knob2.maxValueProperty().doubleValue()) {
                    knob2.setValue(knob2.getValue() + 0.5);
                    wyswietlanieczestotliwosc.setText(knob2.getValue() + "Hz");
                    if(knob2.getValue()>93&&knob2.getValue()<95){
                        aktualneradio.setVolume(0);
                        aktualneradio = mediaPlayerRadio1;
                        aktualneradio.setVolume((knob.getValue()*2.0)/100.0);
                    }
                    else if(knob2.getValue()>100 && knob2.getValue()<102){
                        aktualneradio.setVolume(0);
                        aktualneradio = mediaPlayerRadio2;
                        aktualneradio.setVolume((knob.getValue()*2.0)/100.0);
                    }
                    else{
                        aktualneradio.setVolume(0);
                        aktualneradio = mediaPlayerRadiozaklocenia;
                        aktualneradio.setVolume((knob.getValue()*2.0)/100.0);
                    }
                }
                else if (dragHelp[0] < 0 && knob2.getValue() > knob2.minValueProperty().doubleValue()) {
                    knob2.setValue(knob2.getValue() - 0.5);
                    wyswietlanieczestotliwosc.setText(knob2.getValue() + "Hz");
                    if(knob2.getValue()>93&&knob2.getValue()<95){
                        aktualneradio.setVolume(0);
                        aktualneradio = mediaPlayerRadio1;
                        aktualneradio.setVolume((knob.getValue()*2.0)/100.0);
                    }
                    else if(knob2.getValue()>100 && knob2.getValue()<102){
                        aktualneradio.setVolume(0);
                        aktualneradio = mediaPlayerRadio2;
                        aktualneradio.setVolume((knob.getValue()*2.0)/100.0);
                    }
                    else{
                        aktualneradio.setVolume(0);
                        aktualneradio = mediaPlayerRadiozaklocenia;
                        aktualneradio.setVolume((knob.getValue()*2.0)/100.0);
                    }
                }
            }
        });
        Thread thread = new Thread(new TimeUpdater());
        thread.start();
        wyswietlanievolume.setText("Volume: " + knob.getValue());
        wyswietlanieczestotliwosc.setText(knob2.getValue() + "Hz");
        /////////////////////////////////////dodawanie elementow do sceny
        StackPane root = new StackPane();
        root.setId("pane");
        root.getChildren().add(button);
        root.getChildren().add(wyswietlanievolume);
        root.getChildren().add(glosnik);
        root.getChildren().add(knob);
        root.getChildren().add(knob2);
        root.getChildren().add(wyswietlanieczestotliwosc);
        root.getChildren().add(wyswietlaniegodziny);
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public class TimeUpdater implements  Runnable {
        String czas;

        @Override
        public void run() {
            while (true) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date date = new Date(System.currentTimeMillis());
                czas = formatter.format(date);
                 Platform.runLater(()->
                         wyswietlaniegodziny.setText(czas));
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

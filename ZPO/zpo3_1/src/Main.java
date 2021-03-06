import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Makieta.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        primaryStage.setTitle("Wyscig kolarski");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();


    }
}

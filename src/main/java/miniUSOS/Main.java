package miniUSOS;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Context.getInstance().setCurrentLocale(new Locale("pl"));
        ResourceBundle bundle = ResourceBundle.getBundle("ooo", Context.getInstance().getCurrentLocale());
        Parent root = loader.load(getClass().getResource("/LoggingScreen.fxml"), bundle);
        primaryStage.setOnCloseRequest(e -> exitApp());
        primaryStage.setTitle("miniUSOS");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void exitApp() {
        Platform.exit();
        System.exit(0);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

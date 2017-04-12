package miniUSOS;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Kasia on 06.04.2017.
 */
public class GroupScreenController {

    public AnchorPane mainPane;

    public void switchToStudent() throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource("StudentScreen.fxml"));
        Scene groupScreen = new Scene(rootTopic);
        Stage stage;
        stage =(Stage) mainPane.getScene().getWindow();
        stage.setScene(groupScreen);
        stage.show();
    }

    public void switchToCourse() throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource("CourseScreen.fxml"));
        Scene groupScreen = new Scene(rootTopic);
        Stage stage;
        stage =(Stage) mainPane.getScene().getWindow();
        stage.setScene(groupScreen);
        stage.show();
    }
}

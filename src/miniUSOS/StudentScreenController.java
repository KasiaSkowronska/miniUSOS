package miniUSOS;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentScreenController {


    public AnchorPane mainPane;

    public void switchToGroup() throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource("GroupScreen.fxml"));
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

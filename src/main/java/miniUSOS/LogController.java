package miniUSOS;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created by Kasia on 13.04.2017.
 */
public class LogController extends AbstractController {

    public TextField loginField;
    public TextField PasswordField;
    public Button loggingButton;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        mainField = mainPane;
    }

    public void logIn() throws IOException {
        Student loggedStudent = new Student();
        loggedStudent.setName(loginField.getText());
        Context.getInstance().setLoggedStudent(loggedStudent);
        switchToStudent();
    }
}

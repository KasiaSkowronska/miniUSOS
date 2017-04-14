package miniUSOS;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kasia on 13.04.2017.
 */
public class LogController extends AbstractController {

    public TextField loginField;
    public TextField PasswordField;
    public Button loggingButton;
    public AnchorPane mainPane;
    public ListView userList;

    @FXML
    public void initialize(){

        mainField = mainPane;
        loadList();
        setListProperty();
    }

    public void logIn() throws IOException {
        Student loggedStudent = new Student();
        loggedStudent.setName(loginField.getText());
        Context.getInstance().setLoggedStudent(loggedStudent);
        switchToStudent();
    }

    public void loadList(){
        List<Student> students = retrieveStudents();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Student student : students) {
            items.add(student.getNick());
        }
        userList.setItems(items);
    }

    public void setListProperty(){
        userList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String old_val, String new_val) {
                        loginField.setText(new_val);
                    }
                });
    }


    public void showDialog() throws IOException {
        switchWindow("/NewUserScreen.fxml");
    }
}

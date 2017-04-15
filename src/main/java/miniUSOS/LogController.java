package miniUSOS;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        setListProperty();
        loadList();
    }

    public void logIn() throws IOException {
        Student loggedStudent = new Student();
        loggedStudent.setName(loginField.getText());
        Context.getInstance().setLoggedStudent(loggedStudent);
        switchToStudent();
    }

    public void loadList(){
        List<Student> students = retrieveStudents();
        ObservableList<Student> items = FXCollections.observableArrayList();
        for (Student student : students) {
            items.add(student);
        }
        userList.setItems(items);
    }

    public void setListProperty(){
        userList.setCellFactory(param -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getNick() == null) {
                    setText(null);
                } else {
                    setText(item.getNick());
                }
            }
        });
        userList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Student student = (Student) newValue;
                        loginField.setText(student.getNick());
                    }
                });
    }


    public void showDialog() throws IOException {
        switchWindow("/NewUserScreen.fxml");
    }
}

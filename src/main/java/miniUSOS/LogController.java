package miniUSOS;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Kasia on 13.04.2017.
 */
public class LogController extends AbstractController {

    public TextField loginField;
    public PasswordField passwordField;
    public Button loggingButton;
    public AnchorPane mainPane;
    public ListView userList;
    public Button angButton;


    @FXML
    public void initialize(){
        //resources = Context.getInstance().getBundle();
        mainField = mainPane;
        fxml = "/LoggingScreen.fxml";
        setListProperty();
        loadList();
        setEnterLogging(passwordField);
        setEnterLogging(loginField);
        setEnterLogging(mainPane);
        //loggingButton.setText(resources.getString("logButton"));
    }

    public void setEnterLogging(Parent field) {
        field.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        logIn();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void logIn() throws IOException {
        if (verifyStudent(loginField.getText(), passwordField.getText())) {
            Student loggedStudent = retrieveStudent(loginField.getText());
            Context.getInstance().setLoggedStudent(loggedStudent);
            switchToStudent();
        } else {
            showAlert();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wrong password for " + loginField.getText() +
                "."  + " Would you like to try again?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            passwordField.setText("");
        }
        if (alert.getResult() == ButtonType.NO) {
            loginField.setText("");
            passwordField.setText("");
        }
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

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        userList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Student student = (Student) newValue;
                        loginField.setText(student.getName());
                    }
                });
    }

    public void showDialog() throws IOException {
        switchWindow("/NewUserScreen.fxml");
    }




}

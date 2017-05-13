package miniUSOS.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.User;
import miniUSOS.Context;
import miniUSOS.Classes.Student;

import java.io.IOException;
import java.util.List;

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
        fxml = "/Screens/LoggingScreen.fxml";
        setListProperty();
        loadList();
        setEnterLogging(passwordField);
        setEnterLogging(loginField);
        setEnterLogging(mainPane);
        //loggingButton.setText(resources.getString("logButton"));
        setAdmin();
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
        if (verifyUser(loginField.getText(), passwordField.getText())) {
            User loggedUser = retrieveUser(loginField.getText());
            Context.getInstance().setLoggedUser(loggedUser);
            switchToStart();
        } else {
            showAlert();
        }
    }

    public void setAdmin(){
        loginField.setText("admin");
        passwordField.setText("admin");
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
        List<User> users = retrieveUsers();
        ObservableList<User> items = FXCollections.observableArrayList();
        for (User user : users) {
            items.add(user);
        }
        userList.setItems(items);
    }

    public void setListProperty(){
        userList.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
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
                        User user = (User) newValue;
                        loginField.setText(user.getName());
                    }
                });
    }

    public void showDialog() throws IOException {
        switchWindow("/Screens/NewUserScreen.fxml");
    }




}

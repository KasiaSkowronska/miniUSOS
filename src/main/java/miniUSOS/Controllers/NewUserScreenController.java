package miniUSOS.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Utils.PersistenceService;
import miniUSOS.Classes.Student;

import javax.persistence.EntityManager;
import java.io.IOException;

/**
 * Created by Kasia on 15.04.2017.
 */
public class NewUserScreenController extends AbstractController {
    public TextField nameField;
    public Button signInButton;
    public TextField passwordField;
    public AnchorPane mainPane;
    public RadioButton studentButton;
    public RadioButton lecturerButton;

    private ToggleGroup entityChosing = new ToggleGroup();

    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/NewUserScreen.fxml";
        manageButtons();

    }

    private void manageButtons() {
        studentButton.setToggleGroup(entityChosing);
        lecturerButton.setToggleGroup(entityChosing);
    }

    public void addUser() throws IOException {
        RadioButton selectedButton = (RadioButton) entityChosing.getSelectedToggle();
    }


    public void addStudent() throws IOException {
        Student student = new Student();
        student.setName(nameField.getText());
        student.setPassword(passwordField.getText());
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        switchWindow("/Screens/LoggingScreen.fxml");
    }
}

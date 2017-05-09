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

import static sun.management.snmp.util.JvmContextFactory.getUserData;

/**
 * Created by Kasia on 15.04.2017.
 */
public class NewUserScreenController extends AbstractController {
    public TextField nameField;
    public Button signInButton;
    public TextField passwordField;
    public AnchorPane mainPane;

    public ToggleGroup entityChosing;
    public RadioButton lecturerButton;
    public RadioButton adminButton;
    public RadioButton studentButton;


    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/NewUserScreen.fxml";
        entityChosing = new ToggleGroup();
        studentButton.setUserData("student");
        lecturerButton.setUserData("lecturer");
        adminButton.setUserData("admin");
        studentButton.setToggleGroup(entityChosing);
        lecturerButton.setToggleGroup(entityChosing);
        adminButton.setToggleGroup(entityChosing);
    }

    public void addUser() throws IOException {
        RadioButton chosenButton = (RadioButton) entityChosing.getSelectedToggle();
        String userType = (String) chosenButton.getUserData();
        System.out.println(userType);
        if (userType.equals("student")){
            addStudent();
        }
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

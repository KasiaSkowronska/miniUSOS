package miniUSOS;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.IOException;

/**
 * Created by Kasia on 15.04.2017.
 */
public class NewUserScreenController extends AbstractController{
    public TextField nameField;
    public Button signInButton;
    public TextField passwordField;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        mainField = mainPane;
    }


    public void addStudent() throws IOException {
        Student student = new Student();
        student.setName(nameField.getText());
        student.setPassword(passwordField.getText());
        SessionFactory sf = buildSessionFactory();
        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        sf.close();
        switchWindow("/LoggingScreen.fxml");

    }
}

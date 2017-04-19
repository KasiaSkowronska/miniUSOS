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
        // TODO: Save student to a group.
        System.out.println("This function is not implemented yet");
        switchWindow("/LoggingScreen.fxml");

    }
}

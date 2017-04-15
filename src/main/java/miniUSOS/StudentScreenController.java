package miniUSOS;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.SQLException;

public class StudentScreenController extends AbstractController{


    public AnchorPane mainPane;
    public ImageView imageView;
    public Label nameField;


    @FXML
    public void initialize(){

        mainField = mainPane;
        Image image = new Image("/konik.jpg");
        imageView.setImage(image);
        nameField.setText(Context.getInstance().getLoggedStudent().getNick());
    }


    public void logOut() throws IOException {
        Context.getInstance().setLoggedStudent(null);
        switchToLogging();
    }



}

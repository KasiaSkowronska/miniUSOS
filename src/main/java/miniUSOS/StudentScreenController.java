package miniUSOS;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StudentScreenController extends AbstractController{


    public AnchorPane mainPane;
    public ImageView imageView;
    public Label nameField;



    @FXML
    public void initialize(){

        mainField = mainPane;
        fxml = "/StudentScreen.fxml";
        Image image = new Image("/konik.jpg");
        imageView.setImage(image);
        nameField.setText(Context.getInstance().getLoggedStudent().getName());
    }


    public void logOut() throws IOException {
        Context.getInstance().setLoggedStudent(null);
        switchToLogging();
    }



}

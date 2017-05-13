package miniUSOS.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Admin;
import miniUSOS.Classes.Lecturer;
import miniUSOS.Classes.Student;
import miniUSOS.Classes.User;
import miniUSOS.Context;

/**
 * Created by Kasia on 01.05.2017.
 */
public class StartScreenController extends AbstractController {

    public AnchorPane mainPane;
    public ImageView imageView;
    public Label nameField;
    public User activeUser = Context.getInstance().getLoggedUser();
    public Label idField;

    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/StartScreen.fxml";
        if (activeUser instanceof Student) {
            Image image = new Image("/avatars/konik.jpg");
            imageView.setImage(image);
            nameField.setText(activeUser.getName());
            idField.setText(activeUser.getId().toString());
        }
        if (activeUser instanceof Lecturer) {
            Image image = new Image("/avatars/lecturer.jpg");
            imageView.setImage(image);
            nameField.setText(activeUser.getName());
        }
        if (activeUser instanceof Admin) {
            Image image = new Image("/avatars/admin.jpg");
            imageView.setImage(image);
            nameField.setText(activeUser.getName());
        }
        }

}

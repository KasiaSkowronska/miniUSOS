package miniUSOS.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Context;
import miniUSOS.Controllers.AbstractController;

/**
 * Created by Kasia on 01.05.2017.
 */
public class StartScreenController extends AbstractController {

    public AnchorPane mainPane;
    public ImageView imageView;
    public Label nameField;

    @FXML
    public void initialize(){

        mainField = mainPane;
        fxml = "/Screens/StartScreen.fxml";
        Image image = new Image("/konik.jpg");
        imageView.setImage(image);
        nameField.setText(Context.getInstance().getLoggedStudent().getName());
    }

}

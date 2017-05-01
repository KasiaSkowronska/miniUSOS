package miniUSOS;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StudentScreenController extends AbstractController{


    public AnchorPane mainPane;



    @FXML
    public void initialize(){

        mainField = mainPane;
        fxml = "/StudentScreen.fxml";
    }





}

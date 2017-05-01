package miniUSOS.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Controllers.AbstractController;

public class StudentScreenController extends AbstractController {


    public AnchorPane mainPane;



    @FXML
    public void initialize(){

        mainField = mainPane;
        fxml = "/Screens/StudentScreen.fxml";
    }





}

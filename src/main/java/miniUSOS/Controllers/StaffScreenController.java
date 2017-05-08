package miniUSOS.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Student;

/**
 * Created by Kasia on 02.05.2017.
 */
public class StaffScreenController extends AbstractController{


    public AnchorPane mainPane;


    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/StaffScreen.fxml";
    }
}

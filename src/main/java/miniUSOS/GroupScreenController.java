package miniUSOS;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Kasia on 06.04.2017.
 */
public class GroupScreenController extends AbstractController {

    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        mainField = mainPane;
    }

}

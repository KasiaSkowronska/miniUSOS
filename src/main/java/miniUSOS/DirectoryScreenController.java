package miniUSOS;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Kasia on 01.05.2017.
 */
public class DirectoryScreenController extends AbstractController {

    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/DirectoryScreen.fxml";
    }
}

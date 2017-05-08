package miniUSOS.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Context;
import miniUSOS.Classes.Group;
import miniUSOS.Classes.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasia on 06.04.2017.
 */
public class GroupScreenController extends AbstractController {

    public AnchorPane mainPane;
    public ListView groupList;
    protected String activeGroup;
    protected Student activeStudent;

    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/GroupScreen.fxml";
        vievGroups();
    }

    private void vievGroups() {
        loadList();
        setListProperty();
    }

    public void loadList(){
        List<Group> groups = new ArrayList<>(Context.getInstance().getLoggedStudent().getGroups());
        ObservableList<Group> items = FXCollections.observableArrayList();
        for (Group group : groups) {
            items.add(group);
        }
        groupList.setItems(items);
    }

    public void setListProperty(){
//        groupList.setCellFactory(param -> new ListCell<Group>() {
//            @Override
//            protected void updateItem(Group item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty || item == null || item.getName() == null) {
//                    setText(null);
//                } else {
//                    setText(item.getName());
//                }
//            }
//        });
//        groupList.getSelectionModel().selectedItemProperty().addListener(
//                new ChangeListener() {
//                    @Override
//                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                        Group group = (Group) newValue;
//                        activeGroup = group.getName();
//                    }
//                });
    }

    public void resignFromGroup(ActionEvent actionEvent) {
    }
}

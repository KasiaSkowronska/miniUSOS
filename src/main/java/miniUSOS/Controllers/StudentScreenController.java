package miniUSOS.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Course;
import miniUSOS.Classes.Group;
import miniUSOS.Context;

import java.util.ArrayList;
import java.util.List;

public class StudentScreenController extends AbstractController {


    public AnchorPane mainPane;
    public ListView coursesList;
    public TableColumn hourCol;
    public TableColumn<Group, String> moCol;
    public TableColumn<Group, String> tuCol;
    public TableColumn<Group, String> wdCol;
    public TableColumn<Group, String> thCol;
    public TableColumn<Group, String> frCol;

    protected String activeGroup;


    @FXML
    public void initialize(){

        mainField = mainPane;
        fxml = "/Screens/StudentScreen.fxml";
        viewMyCourses();
        setSchedule();
    }

    private void viewMyCourses() {
        loadList();
        setListProperty();
    }

    public void loadList(){
        List<Group> groups = new ArrayList<>(Context.getInstance().getLoggedStudent().getGroups());
        ObservableList<Group> items = FXCollections.observableArrayList();
        for (Group group : groups) {
            items.add(group);
        }
        coursesList.setItems(items);
    }

    public void setListProperty(){
        coursesList.setCellFactory(param -> new ListCell<Group>() {
            @Override
            protected void updateItem(Group item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        coursesList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Group group = (Group) newValue;
                        activeGroup = group.getName();
                    }
                });
    }


    public void setSchedule(){
        moCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        tuCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        wdCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        thCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        frCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        ObservableList<String> hours = FXCollections.observableArrayList();
        for (int i = 8; i < 18; i++) {
            String hour = String.valueOf(i);
            hours.add(hour);
        }
        //hourCol.getColumns().addAll(hours);
}


}

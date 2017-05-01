package miniUSOS.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Course;
import miniUSOS.Controllers.AbstractController;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kasia on 01.05.2017.
 */
public class DirectoryScreenController extends AbstractController {

    public AnchorPane mainPane;
    public TableView<Course> courseTable;
    public TableColumn<Course, String> idCol;
    public TableColumn<Course, String> courseCol;
    public TableColumn<Course, String> facultyCol;

    private ObservableList<Course> data;

    @FXML
    public void initialize() {
        mainField = mainPane;
        fxml = "/Screens/DirectoryScreen.fxml";
        idCol.setCellValueFactory(new PropertyValueFactory<Course, String>("ects"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        facultyCol.setCellValueFactory(new PropertyValueFactory<Course, String>("abundance"));

        loadList();
    }


    public void loadList(){
        List<Course> courses = retrieveCourses();
        ObservableList<Course> items = FXCollections.observableArrayList();
        for (Course course : courses) {
            items.add(course);
        }
        courseTable.setItems(items);
    }

    public void addCourse(ActionEvent actionEvent) throws IOException {
        switchToNewCourse();
    }

}
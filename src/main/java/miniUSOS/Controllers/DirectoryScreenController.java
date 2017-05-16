package miniUSOS.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Course;
import miniUSOS.Context;

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

    private Course activeCourse;
    private ObservableList<Course> data;

    @FXML
    public void initialize() {
        mainField = mainPane;
        fxml = "/Screens/DirectoryScreen.fxml";

        setTableProperty();
        loadTable();
    }


    public void loadTable(){
        List<Course> courses = retrieveCourses();
        ObservableList<Course> items = FXCollections.observableArrayList();
        for (Course course : courses) {
            items.add(course);
        }
        courseTable.setItems(items);
    }

    public void setTableProperty(){
        idCol.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));
        courseCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));
        facultyCol.setCellValueFactory(new PropertyValueFactory<Course, String>("faculty"));

    }

    public void addCourse(ActionEvent actionEvent) throws IOException {
        switchToNewCourse();
    }

    public void removeCourse(ActionEvent actionEvent){
        activeCourse = courseTable.getSelectionModel().getSelectedItem();
        removeCourse(activeCourse);
    }


    public void test(MouseEvent mouseEvent) {
        Course kurs = courseTable.getSelectionModel().getSelectedItem();
        System.out.println(kurs.getName());
    }


    public void chooseCourse(ActionEvent actionEvent) throws IOException {
        activeCourse = courseTable.getSelectionModel().getSelectedItem();
        Context.getInstance().setDirectoryContext(activeCourse);
        // Will send a request here right ? and should give feedback to user
        switchToCoursePage();
    }
}
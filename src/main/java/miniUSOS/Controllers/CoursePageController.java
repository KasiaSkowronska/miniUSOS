package miniUSOS.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import miniUSOS.Classes.Course;
import miniUSOS.Classes.Group;
import miniUSOS.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasia on 03.05.2017.
 */
public class CoursePageController extends AbstractController {

    public AnchorPane mainPane;

    public Label courseNameLabel;
    public Label courseCodeLabel;
    public Label facultyLabel;
    public Label ectsLabel;
    public Label placesLabel;
    public Label sylabusLabel;
    public VBox groupOptionsPane;


    private Course activeCourse;



    @FXML
    public void initialize() {
        mainField = mainPane;
        fxml = "/Screens/CoursePage.fxml";
        activeCourse = Context.getInstance().getDirectoryContext();
        setLabels();
        generateGroups();

    }

    public void setLabels(){
        courseNameLabel.setText(activeCourse.getName());
        courseCodeLabel.setText(activeCourse.getCode());
        facultyLabel.setText(activeCourse.getFaculty());
        ectsLabel.setText(String.valueOf(activeCourse.getEcts()));
        placesLabel.setText(String.valueOf(activeCourse.getPlaces()));
        sylabusLabel.setText(activeCourse.getSylabus());
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Context.getInstance().setDirectoryContext(null);
        switchToDirectory();
    }

    public void generateGroups(){
        List<Group> groups = activeCourse.getGroups();
        ToggleGroup toggle = new ToggleGroup();
        for (Group group : groups){
            CheckBox box = new CheckBox();
            box.setUserData(group);
            box.setText(group.toString());
            groupOptionsPane.getChildren().add(box);
        }
    }

    public void sendRequest(){

    }

    public void getSelectedGroups(){
        
    }
}

package miniUSOS.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Course;
import miniUSOS.Context;

import java.io.IOException;

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

    private Course activeCourse;



    @FXML
    public void initialize() {
        mainField = mainPane;
        fxml = "/Screens/CoursePage.fxml";
        activeCourse = Context.getInstance().getDirectoryContext();
        setLabels();

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
}

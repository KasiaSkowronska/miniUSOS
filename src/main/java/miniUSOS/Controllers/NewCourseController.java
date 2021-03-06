package miniUSOS.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Course;
import miniUSOS.Classes.Group;
import miniUSOS.Classes.Lecturer;
import miniUSOS.Utils.PersistenceService;
import miniUSOS.Classes.Student;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kosss on 19.04.2017.
 */
public class NewCourseController extends AbstractController {

    public AnchorPane mainPane;
    private Course newCourse;

    // COURSE PROPERTIES
    public TextField nameField;
    public TextField courseCodeField;
    public TextField facultyField;
    public TextField ectsField;
    public TextField placesField;
    public TextField sylabusField;

    // GROUP PROPERTIES
    public TextField groupNoField;
    public ComboBox groupTypeBox;
    public ComboBox groupTutorBox;
    public ComboBox dayBox;
    public ComboBox hourBox;
    public ListView<Group> groupList;


    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/NewCourseScreen.fxml";
        newCourse = new Course();
        setBoxes();
        setListProperty();
    }

    public void addCourse(ActionEvent actionEvent) throws IOException {
        newCourse.setName(nameField.getText());
        newCourse.setCode(courseCodeField.getText());
        newCourse.setFaculty(facultyField.getText());
        newCourse.setEcts(Integer.parseInt(ectsField.getText()));
        newCourse.setPlaces(Integer.parseInt(placesField.getText()));
        newCourse.setSylabus(sylabusField.getText());

        List<Group> groups = groupList.getItems();
        newCourse.setGroups(groups);

        EntityManager em = PersistenceService.getEntityManager();
        PersistenceService.runTransactional(() -> {
            groups.forEach(em::persist);
            em.persist(newCourse);
        });
//        switchToDirectory();
        switchBack();
    }

    public void addGroup(ActionEvent actionEvent) {
        Group group = new Group();
        group.setNumber(Integer.parseInt(groupNoField.getText()));
        group.setType((String) groupTypeBox.getSelectionModel().getSelectedItem());
        Lecturer tutor = (Lecturer) ((Label) (groupTutorBox.getSelectionModel().getSelectedItem())).getUserData();
        group.setLecturer(tutor);
        String time = dayBox.getSelectionModel().getSelectedItem() + " " + hourBox.getSelectionModel().getSelectedItem();
        group.setTime(time);
        group.setStudents(new ArrayList<>());
        group.setCourse(newCourse);

        groupList.getItems().add(group);
        clearGroupFields();
    }

    public void removeGroup(){
        groupList.getItems().remove(groupList.getSelectionModel().getSelectedItem());
    }

    public void setBoxes(){
        groupTypeBox.getItems().add("Wykład");
        groupTypeBox.getItems().add("Ćwiczenia");
        groupTypeBox.getItems().add("Konwersatorium");
        groupTypeBox.getItems().add("Laboratorium");

        dayBox.getItems().add("PON");
        dayBox.getItems().add("WT");
        dayBox.getItems().add("ŚR");
        dayBox.getItems().add("CZW");
        dayBox.getItems().add("PT");

        List<Lecturer> lecturers = retrieveLecturers();
        for (Lecturer lecturer : lecturers){
            Label label = new Label();
            label.setUserData(lecturer);
            label.setText(lecturer.getName());
            groupTutorBox.getItems().add(label);
        }

        for (int i = 8; i < 18; i++){
            String hour = String.valueOf(i) + ":00";
            hourBox.getItems().add(hour);
        }
    }

    public void setListProperty() {
        groupList.setCellFactory(param -> new ListCell<Group>() {
            @Override
            protected void updateItem(Group item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getNumber() == null) {
                    setText(null);
                } else {
                    String text = "gr: " + String.valueOf(item.getNumber()) + ", " + item.getType() + ", " + item.getLecturer().getName();;
                    setText(text);
                }
            }
        });
    }

    public void clearGroupFields(){
        groupNoField.clear();
        groupTypeBox.getSelectionModel().clearSelection();
        groupTutorBox.getSelectionModel().clearSelection();
        dayBox.getSelectionModel().clearSelection();
        hourBox.getSelectionModel().clearSelection();
    }

}

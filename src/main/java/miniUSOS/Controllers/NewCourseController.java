package miniUSOS.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.Course;
import miniUSOS.Classes.Group;
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
    public TextField nameField;
    public TextField ectsField;
    public TextField abundanceField;
    public TextField sylabusField;
    public AnchorPane mainPane;

    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/NewCourseScreen.fxml";
    }

    public void addCourse(ActionEvent actionEvent) throws IOException {
        Course course = new Course();
        course.setEcts(Integer.parseInt(ectsField.getText()));
        course.setAbundance(Integer.parseInt(abundanceField.getText()));
        course.setSylabus(sylabusField.getText());
        course.setName(nameField.getText());

        List<Group> groups = new ArrayList<>();
        Group group = new Group();
        group.setName(nameField.getText());
        group.setStudents(new ArrayList<Student>());
        group.setCourse(course);
        groups.add(group);
        course.setGroups(groups);

        EntityManager em = PersistenceService.getEntityManager();
        PersistenceService.runTransactional(() -> {
            groups.forEach(em::persist);
            em.persist(course);
        });
        switchWindow("/Screens/CourseScreen.fxml");
    }

}

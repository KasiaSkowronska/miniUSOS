package miniUSOS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

import java.io.IOException;
import java.util.HashSet;

import static miniUSOS.AbstractController.buildSessionFactory;

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
    }

    public void addCourse(ActionEvent actionEvent) throws IOException {
        Course course = new Course();
        course.setEcts(Integer.parseInt(ectsField.getText()));
        course.setAbundance(Integer.parseInt(abundanceField.getText()));
        course.setSylabus(sylabusField.getText());
        course.setName(nameField.getText());

        Group group = new Group();
        group.setName(nameField.getText());
        group.setStudents(new HashSet<Student>());
        group.setCourse(course);
        course.setGroup(group);


        SessionFactory sf = buildSessionFactory();
        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        em.close();
        sf.close();
        switchWindow("/CourseScreen.fxml");
    }

}

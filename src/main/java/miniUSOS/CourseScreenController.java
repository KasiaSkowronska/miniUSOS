package miniUSOS;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Kasia on 06.04.2017.
 */
public class CourseScreenController extends AbstractController {

    public AnchorPane mainPane;
    public ListView courseList;
    public TextField nameField;
    public TextField ectsField;
    public TextField abundanceField;
    public TextField sylabusField;
    private Course activeCourse;

    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/CourseScreen.fxml";
        vievCourses();
    }

    void vievCourses() {
        loadList();
        setListProperty();
    }

    public void registerToCourse(ActionEvent actionEvent) {
        // TODO: check does it is implemented well
        Student activeStudent = Context.getInstance().getLoggedStudent();
        if (activeCourse == null) {
            System.out.println("Kurs to null");
        }
        Group activeGroup = activeCourse.getGroups().get(0); // should be changed to group choosing
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        String studentName = activeStudent.getName();
        List<Student> students = em.createQuery("from Student where name=:studentName")
                .setParameter("studentName", studentName)
                .getResultList();
        Student dbStudent = students.get(0);
        dbStudent.getGroups().add(activeGroup);
        em.persist(dbStudent);
        em.getTransaction().commit();
    }

    public void addCourse(ActionEvent actionEvent) throws IOException {
        switchToNewCourse();
    }

    public void removeCourse(ActionEvent actionEvent){
        removeCourse(activeCourse);
    }

    public void loadList(){
        List<Course> courses = retrieveCourses();
        ObservableList<Course> items = FXCollections.observableArrayList();
        for (Course course : courses) {
            items.add(course);
        }
        courseList.setItems(items);
    }

    public void setListProperty(){
        courseList.setCellFactory(param -> new ListCell<Course>() {
            @Override
            protected void updateItem(Course item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        courseList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Course course = (Course) newValue;
                        activeCourse = course;
                        nameField.setText(activeCourse.getName());
                        ectsField.setText(Integer.toString(activeCourse.getEcts()));
                        abundanceField.setText(Integer.toString(activeCourse.getAbundance()));
                        sylabusField.setText(activeCourse.getSylabus());
                    }
                });
    }

}

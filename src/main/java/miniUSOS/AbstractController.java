package miniUSOS;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Kasia on 06.04.2017.
 */
public abstract class AbstractController {

    protected AnchorPane mainField;
    protected String fxml;

    public void switchToStudent() throws IOException {
        switchWindow("/StudentScreen.fxml");
    }

    public void switchToGroup() throws IOException {
        switchWindow("/GroupScreen.fxml");
    }

    public void switchToCourse() throws IOException {
        switchWindow("/CourseScreen.fxml");
    }

    public void switchToLogging() throws IOException {
        switchWindow("/LoggingScreen.fxml");
    }

    public void switchToNewUser() throws IOException {
        switchWindow("/NewUserScreen.fxml");
    }

    public void switchToNewCourse() throws IOException {
        switchWindow("/NewCourseScreen.fxml");
    }

    public void switchWindow(String fxml_name) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("ooo", Context.getInstance().getCurrentLocale());
        Parent rootTopic = FXMLLoader.load(getClass().getResource(fxml_name), bundle);
        Scene screen = new Scene(rootTopic);
        Stage stage;
        stage = (Stage) mainField.getScene().getWindow();
        stage.setScene(screen);
        stage.show();
    }


    public void reload() throws IOException {
        switchWindow(fxml);
    }


    public List<Student> retrieveStudents() {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Student> students = em.createQuery("from Student").getResultList();
        em.getTransaction().commit();
        return students;
    }

    public List<Course> retrieveCourses() {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Course> courses = em.createQuery("from Course").getResultList();
        em.getTransaction().commit();
        return courses;
    }

    public List<Group> retrieveGroups(Student student) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Group> groups = em.createQuery("from Group").getResultList();
        List<Group> studentGroups = new ArrayList<Group>();
        for (Group group : groups){ // should rather be done in database query
            if (group.getStudents().contains(student)) studentGroups.add(group);
        }
        em.getTransaction().commit();
        return studentGroups;
    }

    public Student retrieveStudent(String username) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        Student s = (Student) em.createQuery("from Student where name = :name").setParameter("name", username).getSingleResult();
        em.getTransaction().commit();
        return s;
    }

    public void removeCourse(Course course) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Course where name = " + course.getName()).getResultList();
        em.getTransaction().commit();
        System.out.println("Usunięto + " + course.getName());
    }


    public boolean verifyStudent(String studentName, String password) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Student> students = em.createQuery("from Student where name=:studentName")
                .setParameter("studentName", studentName)
                .getResultList();
        em.getTransaction().commit();
        for (Student student : students) {
            if (student.getPassword().equals(password)) {
                return true;
            }
//            System.out.println("hasło to: " + student.getPassword());
        }
        return false;
    }
}

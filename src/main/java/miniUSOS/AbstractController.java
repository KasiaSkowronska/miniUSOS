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

/**
 * Created by Kasia on 06.04.2017.
 */
public abstract class AbstractController {

    protected AnchorPane mainField;

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
        Parent rootTopic = FXMLLoader.load(getClass().getResource(fxml_name));
        Scene screen = new Scene(rootTopic);
        Stage stage;
        stage = (Stage) mainField.getScene().getWindow();
        stage.setScene(screen);
        stage.show();
    }


    protected static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public List<Student> retrieveStudents() {
        SessionFactory sf = buildSessionFactory();
        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        List<Student> students = em.createQuery("from Student").getResultList();
        em.getTransaction().commit();
        em.close();
        sf.close();
        return students;
    }

    public List<Course> retrieveCourses() {
        SessionFactory sf = buildSessionFactory();
        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        List<Course> courses = em.createQuery("from Course").getResultList();
        em.getTransaction().commit();
        em.close();
        sf.close();
        return courses;
    }

    public List<Group> retrieveGroups(Student student) {
        SessionFactory sf = buildSessionFactory();
        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        List<Group> groups = em.createQuery("from Group").getResultList();
        List<Group> studentGroups = new ArrayList<Group>();
        for (Group group : groups){ // should be done in database query instead of this
            if (group.getStudents().contains(student)) studentGroups.add(group);
        }
        em.getTransaction().commit();
        em.close();
        sf.close();
        return studentGroups;
    }

//    public void removeCourse(Course course) {
//        SessionFactory sf = buildSessionFactory();
//        EntityManager em = sf.createEntityManager();
//        em.getTransaction().begin();
//        em.createQuery("delete from COURSES where id = " + course.getName()).getResultList();
//        em.getTransaction().commit();
//        System.out.println("Usunięto + " + course.getName());
//        em.close();
//        sf.close();
//    }


    public boolean verifyStudent(String studentName, String password){
        SessionFactory sf = buildSessionFactory();
        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        List<Student> students = em.createQuery("from Student where name=:studentName")
                .setParameter("studentName", studentName)
                .getResultList();
        em.getTransaction().commit();
        em.close();
        sf.close();
        for (Student student : students) {
            if (student.getPassword().equals(password)) {
                return true;
            }
//            System.out.println("hasło to: " + student.getPassword());
        }
        return false;
    }
}

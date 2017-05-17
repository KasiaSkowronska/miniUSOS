package miniUSOS.Controllers;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import miniUSOS.*;
import miniUSOS.Classes.*;
import miniUSOS.Utils.PersistenceService;

import javax.persistence.EntityManager;
import java.io.IOException;
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
        switchWindow("/Screens/UserScreen.fxml");
    }

    public void switchToGroup() throws IOException {
        switchWindow("/Screens/GroupScreen.fxml");
    }

    public void switchToCourse() throws IOException {
        switchWindow("/Screens/CourseScreen.fxml");
    }

    public void switchToLogging() throws IOException {
        switchWindow("/Screens/LoggingScreen.fxml");
    }

    public void switchToNewUser() throws IOException {
        switchWindow("/Screens/NewUserScreen.fxml");
    }

    public void switchToNewCourse() throws IOException {
        switchWindow("/Screens/NewCourseScreen.fxml");
    }

    public void switchToDirectory() throws IOException {
        switchWindow("/Screens/DirectoryScreen.fxml");
    }

    public void switchToStart() throws IOException {
        switchWindow("/Screens/StartScreen.fxml");
    }

    public void switchToStaff() throws IOException {
        switchWindow("/Screens/StaffScreen.fxml");
    }

    public void switchToCoursePage() throws IOException {
        switchWindow("/Screens/CoursePage.fxml");
    }

    public void switchWindow(String fxml_name) throws IOException {
        Context.getInstance().setPreviousScreen(Context.getInstance().getCurrentScreen());
        Context.getInstance().setCurrentScreen(fxml_name);
        String currentScreen = Context.getInstance().getCurrentScreen();
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", Context.getInstance().getCurrentLocale());
        Parent rootTopic = FXMLLoader.load(getClass().getResource(fxml_name), bundle);
        Scene screen = new Scene(rootTopic);
        Stage stage;
        stage = (Stage) mainField.getScene().getWindow();
        stage.setScene(screen);
        stage.show();
    }

    public void switchBack() throws IOException {
        switchWindow(Context.getInstance().getPreviousScreen());
    }

    public void reload() throws IOException {
        switchWindow(fxml);
    }

    public void changeToEnglish() throws IOException {
        Locale newLocale = new Locale("");
        Context.getInstance().setCurrentLocale(newLocale);
        reload();
    }

    public void changeToPolish() throws IOException {
        Locale newLocale = new Locale("pl");
        Context.getInstance().setCurrentLocale(newLocale);
        reload();
    }

    public void changeToSpanish() throws IOException {
        Locale newLocale = new Locale("es");
        Context.getInstance().setCurrentLocale(newLocale);
        reload();
    }

    public void sendNotification(User user, String content){
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setContent(content);
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        em.persist(notification);
        em.getTransaction().commit();

    }


    public List<Student> retrieveStudents() {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Student> students = em.createQuery("from Student").getResultList();
        em.getTransaction().commit();
        return students;
    }

    public List<Lecturer> retrieveLecturers() {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Lecturer> lecturers = em.createQuery("from Lecturer").getResultList();
        em.getTransaction().commit();
        return lecturers;
    }

    public List<User> retrieveUsers() {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("from User").getResultList();
        em.getTransaction().commit();
        return users;
    }

    public List<Course> retrieveCourses() {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Course> courses = em.createQuery("from Course").getResultList();
        em.getTransaction().commit();
        return courses;
    }

    public List<Request> retrieveRequests() {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Request> requests = em.createQuery("from Request").getResultList();
        em.getTransaction().commit();
        return requests;
    }

    public List<Notification> retrieveNotifications(User user) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<Notification> userNotifications = em.createQuery(
                "from Notification where user=:user").setParameter("user", user).getResultList();
        em.getTransaction().commit();
        return userNotifications;
    }

    public User retrieveUser(String username) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        User u = (User) em.createQuery("from User where name = :name").setParameter("name", username).getSingleResult();
        em.getTransaction().commit();
        return u;
    }

    public Student retrieveStudent(String username) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        Student s = (Student) em.createQuery("from Student where name = :name").setParameter("name", username).getSingleResult();
        em.getTransaction().commit();
        return s;
    }

    public Lecturer retrieveLecturer(String username) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        Lecturer l = (Lecturer) em.createQuery("from Lecturer where name = :name").setParameter("name", username).getSingleResult();
        em.getTransaction().commit();
        return l;
    }

    // TO DO POPRAWIĆ
    public void removeCourse(Course course) {
        EntityManager em = PersistenceService.getEntityManager();
        PersistenceService.runTransactional(() -> {
                    em.createQuery("delete from Course where id=:id").setParameter("id", course.getId()).executeUpdate();
                    });
        System.out.println("Usunięto + " + course.getName() + " lub wyskoczył błąd bazy danych");
    }


    public void removeRequest(Request request) {
        EntityManager em = PersistenceService.getEntityManager();
        PersistenceService.runTransactional(() -> {
            em.createQuery("delete from Request where id = " + request.getId()).executeUpdate();
        });
        System.out.println("Usunięto + " + request.getId() + " lub wyskoczył błąd bazy danych");
    }


    public boolean verifyUser(String userName, String password) {
        EntityManager em = PersistenceService.getEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("from User where name=:userName")
                .setParameter("userName", userName)
                .getResultList();
        em.getTransaction().commit();
        for (User user : users) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void logOut() throws IOException {
        Context.getInstance().setLoggedUser(null);
        switchToLogging();
    }

    public void setOnlyLecturerContent(List<Node> nodes){
        for (Node node : nodes){
            if (!(Context.getInstance().getLoggedUser() instanceof Lecturer)){
                node.setVisible(false);
            }
        }
    }
    public void setOnlyStudentContent(List<Node> nodes){
        for (Node node : nodes){
            if (!(Context.getInstance().getLoggedUser() instanceof Student)){
                node.setVisible(false);
            }
        }
    }
    public void setOnlyAdminContent(List<Node> nodes){
        for (Node node : nodes){
            if (!(Context.getInstance().getLoggedUser() instanceof Admin)){
                node.setVisible(false);
            }
        }
    }

    public void setNotAdminContent(List<Node> nodes){
        for (Node node : nodes){
            if (Context.getInstance().getLoggedUser() instanceof Admin){
                node.setVisible(false);
            }
        }
    }

}

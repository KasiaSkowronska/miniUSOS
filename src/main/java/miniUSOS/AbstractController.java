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
import java.util.List;

/**
 * Created by Kasia on 06.04.2017.
 */
public abstract class AbstractController {

    protected AnchorPane mainField;

    public void switchToStudent() throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource("/StudentScreen.fxml"));
        Scene groupScreen = new Scene(rootTopic);
        Stage stage;
        stage =(Stage) mainField.getScene().getWindow();
        stage.setScene(groupScreen);
        stage.show();
    }

    public void switchToGroup() throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource("/GroupScreen.fxml"));
        Scene groupScreen = new Scene(rootTopic);
        Stage stage;
        stage =(Stage) mainField.getScene().getWindow();
        stage.setScene(groupScreen);
        stage.show();
    }

    public void switchToCourse() throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource("/CourseScreen.fxml"));
        Scene groupScreen = new Scene(rootTopic);
        Stage stage;
        stage =(Stage) mainField.getScene().getWindow();
        stage.setScene(groupScreen);
        stage.show();
    }

    public void switchToLogging() throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource("/LoggingScreen.fxml"));
        Scene groupScreen = new Scene(rootTopic);
        Stage stage;
        stage =(Stage) mainField.getScene().getWindow();
        stage.setScene(groupScreen);
        stage.show();
    }

    public void switchWindow(String fxml_name) throws IOException {
        Parent rootTopic = FXMLLoader.load(getClass().getResource(fxml_name));
        Scene groupScreen = new Scene(rootTopic);
        Stage stage;
        stage =(Stage) mainField.getScene().getWindow();
        stage.setScene(groupScreen);
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
}

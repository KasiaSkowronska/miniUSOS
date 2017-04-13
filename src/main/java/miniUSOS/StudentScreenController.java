package miniUSOS;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class StudentScreenController extends AbstractController{


    public AnchorPane mainPane;
    public ImageView imageView;
    public Label nameField;


    @FXML
    public void initialize(){

        mainField = mainPane;
        Image image = new Image("/konik.jpg");
        imageView.setImage(image);
        nameField.setText(Context.getInstance().getLoggedStudent().getNick());
    }

    public void createStudent() throws SQLException {
        Student student = new Student();
        student.setName("Konrad");
        SessionFactory sf = buildSessionFactory();
        EntityManager em = sf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        sf.close();
    }



}

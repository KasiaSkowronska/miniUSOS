package miniUSOS;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kasia on 13.04.2017.
 */
public class LogController extends AbstractController {

    public TextField loginField;
    public TextField PasswordField;
    public Button loggingButton;
    public AnchorPane mainPane;
    public ListView userList;

    @FXML
    public void initialize(){

        mainField = mainPane;
        setListProperty();
        loadList();
    }

    public void logIn() throws IOException {
        if (verifyStudent(loginField.getText(), PasswordField.getText())) {
            Student loggedStudent = new Student();
            loggedStudent.setName(loginField.getText());
            Context.getInstance().setLoggedStudent(loggedStudent);
            switchToStudent();
        } else {
            System.out.println("Złe hasło lub login");
        }
    }

    public void loadList(){
        List<Student> students = retrieveStudents();
        ObservableList<Student> items = FXCollections.observableArrayList();
        for (Student student : students) {
            items.add(student);
        }
        userList.setItems(items);
    }

    public void setListProperty(){
        userList.setCellFactory(param -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        userList.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Student student = (Student) newValue;
                        loginField.setText(student.getName());
                    }
                });
    }


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
            System.out.println("hasło to: " + student.getPassword());
        }
        return false;
    }


    public void showDialog() throws IOException {
        switchWindow("/NewUserScreen.fxml");
    }
}

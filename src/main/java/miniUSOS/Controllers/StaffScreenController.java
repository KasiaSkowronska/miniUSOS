package miniUSOS.Controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import miniUSOS.Classes.*;
import miniUSOS.Context;
import miniUSOS.Utils.PersistenceService;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Kasia on 02.05.2017.
 */
public class StaffScreenController extends AbstractController{


    public AnchorPane mainPane;
    public TableView<Request> requestsTable;
    public TableColumn<Request, String> studentCol;
    public TableColumn<Request, String> courseNameCol;
    public TableColumn<Request, String> groupNrCol;


    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/StaffScreen.fxml";
        setTableProperty();
        loadTable();
    }

    public void loadTable(){
        List<Request> requests = retrieveRequests();
        ObservableList<Request> items = FXCollections.observableArrayList();
        for (Request request : requests) {
            items.add(request);
        }
        requestsTable.setItems(items);
    }

    public void setTableProperty(){
        studentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyStringWrapper(p.getValue().getStudent().getName());
            }
        });
        courseNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                    // p.getValue() returns the Person instance for a particular TableView row
                    return new ReadOnlyStringWrapper(p.getValue().getGroup().getCourse().getName());
                }});
        groupNrCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return new ReadOnlyStringWrapper(String.valueOf(p.getValue().getGroup().getNumber()));
            }});

    }

    public void acceptRequest(ActionEvent actionEvent) {
        if (requestsTable.getSelectionModel().getSelectedItem() != null){
            Request request = requestsTable.getSelectionModel().getSelectedItem();
            registerToCourse(request.getStudent(), request.getGroup());
            String content = "Zapisano na kurs: " + request.getGroup().getCourse().getName();
            sendNotification(request.getStudent(), content);
            removeRequest(request);
            loadTable();
        }
    }

    public void rejectRequest(ActionEvent actionEvent) {
    }


    public void registerToCourse(Student student, Group group) {
            EntityManager em = PersistenceService.getEntityManager();
            PersistenceService.runTransactional(() -> {
                Integer studentId = student.getId();
                List<Student> students = em.createQuery("from Student where id=:studentId")
                        .setParameter("studentId", studentId)
                        .getResultList();
                Student dbStudent = students.get(0);
                dbStudent.getGroups().add(group);
                em.persist(dbStudent);
            });
    }


}

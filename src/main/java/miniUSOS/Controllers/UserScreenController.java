package miniUSOS.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import miniUSOS.Classes.Group;
import miniUSOS.Classes.Lecturer;
import miniUSOS.Classes.Student;
import miniUSOS.Classes.User;
import miniUSOS.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserScreenController extends AbstractController {


    public AnchorPane mainPane;

    public TableColumn hourCol;
    public TableColumn<Group, String> moCol;
    public TableColumn<Group, String> tuCol;
    public TableColumn<Group, String> wdCol;
    public TableColumn<Group, String> thCol;
    public TableColumn<Group, String> frCol;

    public TableView userCourseTable;
    public TableColumn courseCol;
    public TableColumn groupIDCol;
    public TableColumn timeCol;
    public Button reportButton;
    public Pane schedulePane;
    public Pane coursesPane;
    public TableView scheduleTable;

    protected String activeGroup;
    public User activeUser = Context.getInstance().getLoggedUser();


    @FXML
    public void initialize(){

        mainField = mainPane;
        fxml = "/Screens/UserScreen.fxml";
        if (activeUser instanceof Student) {
            viewStudentCourses();
            setSchedule();
        }
        if (activeUser instanceof Lecturer) {
            viewLecturerCourses();
            setSchedule();
        }
        setOnlyLecturerContent(new ArrayList<Node>(Arrays.asList(reportButton)));
        setNotAdminContent(new ArrayList<Node>(Arrays.asList(schedulePane, coursesPane, scheduleTable, userCourseTable)));
    }

    private void viewLecturerCourses() {
        loadLecturerList();
        setListProperty();
    }


    private void viewStudentCourses() {
        loadStudentList();
        setListProperty();
    }

    public void loadLecturerList(){
        Lecturer activeLecturer = (Lecturer) activeUser;
        List<Group> groups = new ArrayList<>(activeLecturer.getGroups());
        ObservableList<Group> items = FXCollections.observableArrayList();
        for (Group group : groups) {
            items.add(group);
        }
        userCourseTable.setItems(items);
    }

    public void loadStudentList(){
        Student activeStudent = (Student) activeUser;
        List<Group> groups = new ArrayList<>(activeStudent.getGroups());
        ObservableList<Group> items = FXCollections.observableArrayList();
        for (Group group : groups) {
            items.add(group);
        }
        userCourseTable.setItems(items);
    }

    public void setListProperty(){
        courseCol.setCellValueFactory(new PropertyValueFactory<Group, String>("course"));
        groupIDCol.setCellValueFactory(new PropertyValueFactory<Group, String>("number"));
        timeCol.setCellValueFactory(new PropertyValueFactory<Group, String>("time"));

    }


    public void setSchedule(){
        moCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        tuCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        wdCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        thCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        frCol.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        ObservableList<String> hours = FXCollections.observableArrayList();
        for (int i = 8; i < 18; i++) {
            String hour = String.valueOf(i);
            hours.add(hour);
        }
        //hourCol.getColumns().addAll(hours);
}


}

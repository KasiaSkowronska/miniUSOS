package miniUSOS.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import miniUSOS.Classes.*;
import miniUSOS.Context;

import java.util.List;

/**
 * Created by Kasia on 01.05.2017.
 */
public class StartScreenController extends AbstractController {

    public AnchorPane mainPane;
    public ImageView imageView;
    public Label nameField;
    public User activeUser = Context.getInstance().getLoggedUser();
    public Label idField;

    public ListView notificationList;

    @FXML
    public void initialize(){
        mainField = mainPane;
        fxml = "/Screens/StartScreen.fxml";
        setListProperty();
        loadNotifications();
        if (activeUser instanceof Student) {
            Image image = new Image("/avatars/konik.jpg");
            imageView.setImage(image);
            nameField.setText(activeUser.getName());
            idField.setText(activeUser.getId().toString());
        }
        if (activeUser instanceof Lecturer) {
            Image image = new Image("/avatars/lecturer.jpg");
            imageView.setImage(image);
            nameField.setText(activeUser.getName());
        }
        if (activeUser instanceof Admin) {
            Image image = new Image("/avatars/admin.jpg");
            imageView.setImage(image);
            nameField.setText(activeUser.getName());
        }
        }


    public void loadNotifications(){
        List<Notification> notifications = retrieveNotifications(Context.getInstance().getLoggedUser());
        ObservableList<Notification> items = FXCollections.observableArrayList();
        for (Notification notification : notifications) {
            items.add(notification);
        }
        notificationList.setItems(items);
    }

    public void setListProperty(){
        notificationList.setCellFactory(param -> new ListCell<Notification>() {
            @Override
            protected void updateItem(Notification item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getContent() == null) {
                    setText(null);
                } else {
                    setText(item.getContent());
                }
            }
        });

}}

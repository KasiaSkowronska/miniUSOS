package miniUSOS;

import miniUSOS.Classes.Course;
import miniUSOS.Classes.Student;
import miniUSOS.Classes.User;
import miniUSOS.Controllers.AbstractController;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Kasia on 13.04.2017.
 */
public class Context {

    public String getPreviousScreen() {
        return previousScreen;
    }

    public void setPreviousScreen(String previousScreen) {
        this.previousScreen = previousScreen;
    }

    public String previousScreen;

    public String currentScreen;

    public String getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(String currentScreen) {
        this.currentScreen = currentScreen;
    }

    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private User loggedUser;

    private Course directoryContext;

    public Course getDirectoryContext() {
        return directoryContext;
    }

    public void setDirectoryContext(Course directoryContext) {
        this.directoryContext = directoryContext;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    private Locale currentLocale;

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    private ResourceBundle bundle;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }


}

package miniUSOS;

import miniUSOS.Classes.Course;
import miniUSOS.Classes.Student;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Kasia on 13.04.2017.
 */
public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private Student loggedStudent;

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

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }


}

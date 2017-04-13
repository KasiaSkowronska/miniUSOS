package miniUSOS;

/**
 * Created by Kasia on 13.04.2017.
 */
public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private Student loggedStudent;

    public Student getLoggedStudent() {
        return loggedStudent;
    }

    public void setLoggedStudent(Student loggedStudent) {
        this.loggedStudent = loggedStudent;
    }


}

package miniUSOS;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Kasia on 06.04.2017.
 */
public abstract class AbstractController {

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}

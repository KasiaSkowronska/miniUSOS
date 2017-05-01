package miniUSOS.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

/**
 * Created by kosss on 20.04.2017.
 */
public class PersistenceService {

    protected static EntityManager manager = null;

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

    public synchronized static EntityManager getEntityManager() {
        if (manager == null) {
            manager = buildSessionFactory().createEntityManager();
        }
        return manager;
    }

    public static void runTransactional(Runnable sequence) {
        try {
            getEntityManager().getTransaction().begin();
            sequence.run();
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }
    }

}

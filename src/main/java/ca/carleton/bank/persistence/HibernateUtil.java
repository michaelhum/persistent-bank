package ca.carleton.bank.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Properties;

/**
 * Hibernate utilities for building and returning the session factory.
 * <p>
 * Created by Mike on 06/08/2015.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    //private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bankManager");

    private static SessionFactory buildSessionFactory() {
        try {
            final Configuration configuration = new Configuration();
            configuration.addPackage("ca.carleton.bank.entity")
                    .configure();

            final Properties properties = configuration.getProperties();
            final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (final Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

//    public static EntityManager getEntityManager() {
//        return entityManagerFactory.createEntityManager();
//    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
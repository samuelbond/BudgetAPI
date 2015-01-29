package uk.co.platitech.models;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;

/**
 * Created by Samuel on 11/8/2014.
 */
public class ConnectionManager {


    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure(ConnectionManager.class.getResource("/hibernate.cfg.xml"));
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }



    /**
     * Gets the hibernate session
     * @return Session
     * @throws HibernateException
     */
    public static Session getHibernateSession() throws HibernateException {
        return getSessionFactory().openSession();
    }

    /**
     * Gets the entity manager
     * @return UnsupportedOperationException
     */
    public EntityManager getEntityManager()
    {
        throw new UnsupportedOperationException("Entity Manager is not supported");
    }

    public static void closeHibernateConnection()
    {
        sessionFactory.close();
    }

}

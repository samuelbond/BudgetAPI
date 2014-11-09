package uk.co.platitech.models;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.persistence.EntityManager;

/**
 * Created by Samuel on 11/8/2014.
 */
public class ConnectionManager {


    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;


    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets the hibernate session
     * @return Session
     * @throws HibernateException
     */
    public static Session getHibernateSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    /**
     * Gets the entity manager
     * @return UnsupportedOperationException
     */
    public EntityManager getEntityManager()
    {
        throw new UnsupportedOperationException("Entity Manager is not supported");
    }

}

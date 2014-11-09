package uk.co.platitech.models;

import org.hibernate.Query;
import org.hibernate.Session;
import uk.co.platitech.AppsEntity;
import uk.co.platitech.UsersEntity;

/**
 * Created by Samuel on 11/8/2014.
 */
public class DataRepository {

    private Session session;

    public DataRepository() {
        session = ConnectionManager.getHibernateSession();
    }

    /**
     * Insert a new entity
     * @param obj
     * @return boolean
     */
    public boolean insert(Object obj) {
        try {
            session.beginTransaction();
            session.persist(obj);
            session.getTransaction().commit();
        } catch (Exception ex) {
            return  false;
        }

        return true;
    }

    public AppsEntity getAppByKey(String key) {
        try {
            Query query = session.createQuery("from AppsEntity a where a.appKey = :aKey ");
            query.setParameter("aKey", key);
            return (AppsEntity) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       return null;
    }

    public UsersEntity getUserByUserId(String userId) {
        try {
            Query query = session.createQuery("from UsersEntity u where u.userId = :uId ");
            query.setParameter("uId", userId);
            return (UsersEntity) query.uniqueResult();
        } catch (Exception ex) {
        }
        return null;
    }

    public UsersEntity getUserByEmailAndPassword(String email, String password) {
        try {
            Query query = session.createQuery("from UsersEntity u where u.email = :uEmail and u.password = :uPassword ");
            query.setParameter("uEmail", email);
            query.setParameter("uPassword", password);
            return (UsersEntity) query.uniqueResult();
        } catch (Exception ex) {
        }
        return null;
    }
}

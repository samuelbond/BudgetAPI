package uk.co.platitech.models;

import org.hibernate.Query;
import org.hibernate.Session;
import uk.co.platitech.*;

import java.util.List;

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

    public boolean update(Object obj)
    {
        try {
            session.beginTransaction();
            session.merge(obj);
            session.getTransaction().commit();
        } catch (Exception ex) {
            return  false;
        }

        return true;
    }

    public boolean delete(Object obj) {
        try {
            session.beginTransaction();
            session.delete(obj);
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

    public CurrenciesEntity getCurrencyByCode(String code) {
        try {
            Query query = session.createQuery("from CurrenciesEntity c where c.code = :cCode ");
            query.setParameter("cCode", code);
            return (CurrenciesEntity) query.uniqueResult();
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

    public AccountBalanceEntity getAccountBalanceByAccountId(BankAccountEntity accountId) {
        try {
            Query query = session.createQuery("from AccountBalanceEntity ac where ac.bankAccount = :acBank");
            query.setParameter("acBank", accountId);
            return (AccountBalanceEntity) query.uniqueResult();
        } catch (Exception ex) {
        }
        return null;
    }

    public BankAccountEntity getBankAccountByAccountId(Integer accountId) {
        try {
            Query query = session.createQuery("from BankAccountEntity ac where ac.id = :acBank");
            query.setParameter("acBank", accountId);
            return (BankAccountEntity) query.uniqueResult();
        } catch (Exception ex) {
        }
        return null;
    }

    public BankAccountEntity getBankAccountByAccountIdAndUserId(Integer accountId, UsersEntity usersEntity) {
        try {
            Query query = session.createQuery("from BankAccountEntity ac where ac.id = :acBank and ac.users = :acUsers");
            query.setParameter("acBank", accountId);
            query.setParameter("acUsers", usersEntity);
            return (BankAccountEntity) query.uniqueResult();
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

    public List<BankAccountEntity> fetchAllBankAccount(UsersEntity users) {
        try {
            Query query = session.createQuery("from BankAccountEntity ac where ac.users = :acUser  ");
            query.setParameter("acUser", users);

            return (List<BankAccountEntity>) query.list();
        } catch (Exception ex) {
        }
        return null;
    }


    public List<BudgetsEntity> fetchAllBudget(UsersEntity usersEntity)
    {
        try {
            Query query = session.createQuery("from BudgetsEntity ac where ac.users = :acUser  ");
            query.setParameter("acUser", usersEntity);

            return (List<BudgetsEntity>) query.list();
        } catch (Exception ex) {
        }
        return null;
    }


    public List<AccountTransactionsEntity> fetchAllAccountTransactions(BankAccountEntity accountEntity) {
        try {
            Query query = session.createQuery("from AccountTransactionsEntity ac where ac.bankAccount = :acAcc  ");
            query.setParameter("acAcc", accountEntity);

            return (List<AccountTransactionsEntity>) query.list();
        } catch (Exception ex) {
        }
        return null;
    }

    public List<AccountTransactionsEntity> fetchAllAccountTransactions(BankAccountEntity accountEntity, BudgetsEntity budgetsEntity) {
        try {
            Query query = session.createQuery("from AccountTransactionsEntity ac where ac.bankAccount = :acAcc and ac.budgetEnty = :acBud ");
            query.setParameter("acAcc", accountEntity);
            query.setParameter("acBud", budgetsEntity);

            return (List<AccountTransactionsEntity>) query.list();
        } catch (Exception ex) {
        }
        return null;
    }

    public List<AccountTransactionsEntity> fetchAllBudgetTransactions(BudgetsEntity budgetsEntity) {
        try {
            Query query = session.createQuery("from AccountTransactionsEntity ac where ac.budgetEnty = :acAcc  ");
            query.setParameter("acAcc", budgetsEntity);

            return (List<AccountTransactionsEntity>) query.list();
        } catch (Exception ex) {
        }
        return null;
    }

    public TransactionCategoryEntity getTransactionCategory(String categoryName)
    {
        try {
            Query query = session.createQuery("from TransactionCategoryEntity tc where tc.categoryName = :tcName ");
            query.setParameter("tcName", categoryName);
            return (TransactionCategoryEntity) query.uniqueResult();
        } catch (Exception ex) {
        }
        return null;
    }


    public List<TransactionCategoryEntity> fetchAllTransactionCategories() {
        try {
            Query query = session.createQuery("from TransactionCategoryEntity ");

            return (List<TransactionCategoryEntity>) query.list();
        } catch (Exception ex) {
        }
        return null;
    }
}

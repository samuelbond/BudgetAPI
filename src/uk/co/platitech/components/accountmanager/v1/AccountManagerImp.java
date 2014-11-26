package uk.co.platitech.components.accountmanager.v1;

import uk.co.platitech.*;
import uk.co.platitech.components.accountmanager.AccountManagerInterface;
import uk.co.platitech.helpers.RandomStringGenerator;
import uk.co.platitech.models.DataRepository;

import java.util.List;
import java.util.Timer;

/**
 * Created by Samuel on 11/8/2014.
 */
public class AccountManagerImp implements AccountManagerInterface {

    private DataRepository data;

    public AccountManagerImp() {
        this.data = new DataRepository();
    }


    public List<BankAccountEntity> getUserBankAccounts(String userId)
    {
        return this.data.fetchAllBankAccount(new UsersEntity(userId));
    }

    public AccountBalanceEntity getUserAccountBalance(Integer accountId)
    {
        BankAccountEntity bae = new BankAccountEntity();
        bae.setId(accountId);
        return this.data.getAccountBalanceByAccountId(bae);
    }

    public TransactionCategoryEntity getCategory(String type)
    {
        return this.data.getTransactionCategory(type);
    }


    public Boolean createNewAccount(BankAccountEntity bae, AccountBalanceEntity abe, CurrenciesEntity ce)
    {
        if(abe != null && bae != null && ce != null)
        {
            CurrenciesEntity curr = this.data.getCurrencyByCode(ce.getCode());
            if(curr == null)
            {
                return false;
            }
            bae.setCurrencies(curr);
            try
            {
                abe.setBankAccount(bae);

                this.data.insert(bae);
                abe.setBankAccount(bae);
                this.data.insert(abe);
                return true;
            }catch (Exception ex)
            {

            }
        }

        return false;
    }

    public Boolean verifyAccountBelongsToUser(Integer accountId, String userId)
    {
        try
        {
            UsersEntity ue = new UsersEntity(userId);
            BankAccountEntity bankAccountEntity = this.data.getBankAccountByAccountIdAndUserId(accountId, ue);

            if(bankAccountEntity != null)
            {
                return true;
            }

        }catch (Exception ex)
        {

        }

        return false;
    }

    public List<AccountTransactionsEntity> getAccountTransactions(Integer accountId)
    {
        BankAccountEntity bae = new BankAccountEntity();
        bae.setId(accountId);

        return this.data.fetchAllAccountTransactions(bae);
    }

    public Boolean removeAccount(String accountId)
    {
        try
        {
            BankAccountEntity bankAccountEntity = this.data.getBankAccountByAccountId(Integer.parseInt(accountId));
            if(bankAccountEntity == null)
            {
                return false;
            }
            this.data.delete(bankAccountEntity);
            return true;
        }catch (Exception ex)
        {

        }

        return false;
    }


    public Boolean createNewBudget(BudgetsEntity budgetsEntity)
    {
        try
        {
            this.data.insert(budgetsEntity);
        }catch (Exception ex)
        {
            return false;
        }

        return true;
    }


    public Boolean addNewTransaction(AccountTransactionsEntity accountTransactionsEntity)
    {
        try
        {
            RandomStringGenerator rsg = new RandomStringGenerator(10);
            String id = rsg.generate();
            rsg.setStringLength(5);
            id = id+rsg.generate();
            accountTransactionsEntity.setTransactionId(id);

            AccountBalanceEntity ab = this.getUserAccountBalance(accountTransactionsEntity.getBankAccount().getId());

            if(ab == null)
            {
                return false;
            }

            ab.setLastBalance(ab.getBalance());
            if(accountTransactionsEntity.getTransactionCategory().getCategoryName().equals("CREDIT"))
            {
                ab.setBalance(ab.getBalance() + Long.parseLong(accountTransactionsEntity.getTransactionAmount()));
            }
            else
            {
                ab.setBalance(ab.getBalance() - Long.parseLong(accountTransactionsEntity.getTransactionAmount()));
            }

            this.data.update(ab);
            this.data.insert(accountTransactionsEntity);

            return true;
        }catch (Exception ex)
        {

        }

        return false;
    }
}

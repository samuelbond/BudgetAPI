package uk.co.platitech.components.accountmanager;

import uk.co.platitech.*;

import java.util.List;

/**
 * Created by Samuel on 11/8/2014.
 */
public interface AccountManagerInterface {

    public List<BankAccountEntity> getUserBankAccounts(String userId);

    public Boolean createNewAccount(BankAccountEntity bae, AccountBalanceEntity abe, CurrenciesEntity ce);

    public AccountBalanceEntity getUserAccountBalance(Integer accountId);

    public Boolean verifyAccountBelongsToUser(Integer accountId, String userId);

    public List<AccountTransactionsEntity> getAccountTransactions(Integer accountId);

    public Boolean addNewTransaction(AccountTransactionsEntity accountTransactionsEntity);

    public TransactionCategoryEntity getCategory(String type);
}

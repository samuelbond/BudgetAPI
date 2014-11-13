package uk.co.platitech.components.accountmanager;

import uk.co.platitech.AccountBalanceEntity;
import uk.co.platitech.BankAccountEntity;
import uk.co.platitech.CurrenciesEntity;

import java.util.List;

/**
 * Created by Samuel on 11/8/2014.
 */
public interface AccountManagerInterface {

    public List<BankAccountEntity> getUserBankAccounts(String userId);

    public Boolean createNewAccount(BankAccountEntity bae, AccountBalanceEntity abe, CurrenciesEntity ce);
}

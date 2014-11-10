package uk.co.platitech.components.accountmanager.v1;

import uk.co.platitech.BankAccountEntity;
import uk.co.platitech.UsersEntity;
import uk.co.platitech.components.accountmanager.AccountManagerInterface;
import uk.co.platitech.models.DataRepository;

import java.util.List;

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
}

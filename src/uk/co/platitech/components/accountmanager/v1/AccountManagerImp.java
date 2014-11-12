package uk.co.platitech.components.accountmanager.v1;

import uk.co.platitech.AccountBalanceEntity;
import uk.co.platitech.BankAccountEntity;
import uk.co.platitech.CurrenciesEntity;
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


    public String createNewAccount(BankAccountEntity bae, AccountBalanceEntity abe, CurrenciesEntity ce)
    {
        if(abe != null && bae != null && ce != null)
        {
            bae.setCurrenciesEntity(this.data.getCurrencyByCode(ce.getCode()));
            try
            {
                abe.setBankAccountEntity(bae);

                this.data.insert(bae);
                abe.setAccount(bae.getId());
                this.data.insert(abe);
                return "id: "+bae.getId();
            }catch (Exception ex)
            {
                return ex.getMessage();
            }
        }

        return "failed";
    }
}

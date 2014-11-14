package uk.co.platitech.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import sun.misc.FloatingDecimal;
import uk.co.platitech.*;
import uk.co.platitech.components.accountmanager.v1.AccountManagerImp;
import uk.co.platitech.components.appauthentication.v1.AppAuthenticationImp;
import uk.co.platitech.helpers.BankAccountAdaptor;
import uk.co.platitech.helpers.DataObject;
import uk.co.platitech.helpers.HibernateProxyTypeAdapter;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.Date;
import java.util.List;

import static uk.co.platitech.helpers.HibernateProxyTypeAdapter.FACTORY;

/**
 * Created by samuel on 10/11/14.
 */
@Stateless
@Path("co.uk.platitech.budget.accounts")
public class Accounts {


    @GET
    @Path("{userId}")
    @Produces({"application/json"})
    public String getAccounts(@PathParam("userId") String userId)
    {
        JsonObject value = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(BankAccountEntity.class, new BankAccountAdaptor()).create();
        String output = null;

        AccountManagerImp acct = new AccountManagerImp();
        List<BankAccountEntity> accounts = acct.getUserBankAccounts(userId);

        if(userId.length() != 8)
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Could not validate user");
            output = value.toString();
        }
        else
        {
            if(accounts == null) {
                value.addProperty("status", "success");
                value.addProperty("accounts", "no account");
                output = value.toString();
            }
            else
            {

                value.addProperty("status", "success");
                value.add("list", gson.toJsonTree(accounts));
                output = value.toString();
            }

        }

        return output;
    }

    @POST
    @Path("/create")
    @Produces({"application/json"})
    public String createNewAccount(javax.json.JsonObject json)
    {
        // Accounts Component
        AccountManagerImp ami = new AccountManagerImp();

        JsonObject value = new JsonObject();

        BankAccountEntity bae = new BankAccountEntity();
        bae.setAccountName(json.getString("account_name"));
        bae.setAccountNumber(Long.parseLong(json.getString("account_number")));
        bae.setUsers(new UsersEntity(json.getString("user_id")));
        AccountBalanceEntity abe = new AccountBalanceEntity();
        abe.setBalance((long) json.getInt("start_balance"));
        abe.setLastBalance((long) json.getInt("start_balance"));
        CurrenciesEntity ce = new CurrenciesEntity();
        ce.setCode(json.getString("currency_code"));
        ce.setCountry(json.getString("currency_country"));


        if(ami.createNewAccount(bae,abe,ce))
        {
            value.addProperty("status", "success");
            value.addProperty("message", "account created");
        }
        else {
            value.addProperty("status", "error");
            value.addProperty("error", "could not create new account");
        }


        return value.toString();
    }

    @POST
    @Path("/delete")
    @Produces({"application/json"})
    public String deleteBankAccount(javax.json.JsonObject json)
    {
        // Accounts Component
        AccountManagerImp ami = new AccountManagerImp();

        JsonObject value = new JsonObject();

        if(ami.removeAccount(json.getString("account_id")))
        {
            value.addProperty("status", "success");
            value.addProperty("message", "account deleted");
        }
        else
        {
            value.addProperty("status", "error");
            value.addProperty("error", "could not delete account");
        }

        return value.toString();
    }

    @POST
    @Path("/addtransaction")
    @Produces({"application/json"})
    public String addTransaction(javax.json.JsonObject json)
    {
        AccountTransactionsEntity ate = new AccountTransactionsEntity();
        BankAccountEntity account = new BankAccountEntity();
        account.setId(Integer.parseInt(json.getString("account_id")));
        ate.setBankAccountEntity(account);
        ate.setTransactionAmount(json.getString("trx_amount"));
        TransactionCategoryEntity tce = new TransactionCategoryEntity();
        tce.setId(Integer.parseInt(json.getString("trx_amount")));
        ate.setTransactionCategory(tce);
        ate.setTransactionName(json.getString("trx_name"));
        ate.setTransactionDate(new Date().toString());

        return null;
    }




}

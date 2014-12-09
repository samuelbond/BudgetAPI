package uk.co.platitech.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import uk.co.platitech.*;
import uk.co.platitech.components.accountmanager.v1.AccountManagerImp;
import uk.co.platitech.components.appauthentication.v1.AppAuthenticationImp;
import uk.co.platitech.helpers.AccountTransactionAdaptor;
import uk.co.platitech.helpers.BankAccountAdaptor;
import uk.co.platitech.helpers.BudgetAdaptor;


import javax.ejb.Stateless;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by samuel on 10/11/14.
 */
@Stateless
@Path("co.uk.platitech.budget.accounts")
public class Accounts {

    protected class validation
    {
        private List<String> keys;
        private javax.json.JsonObject json;
        private StringBuilder missing;

        public validation(javax.json.JsonObject json, List<String> keys) {
            this.json = json;
            this.keys = keys;
            this.missing = new StringBuilder();
        }


        public boolean doValidation()
        {
            for (String key : this.keys) {
                if (this.json.containsKey(key)) {
                    if (this.json.isNull(key)) {
                        this.missing.append("\"").append(key).append("\"").append(" must not be empty or null, ");
                        return false;
                    }
                }
                else
                {
                    this.missing.append("\"").append(key).append("\"").append(" must be set, ");
                    return false;
                }
            }

            return true;
        }

        public String getFailedParameters()
        {
            return this.missing.toString();
        }
    }

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
    @Path("/transaction")
    @Produces({"application/json"})
    public String getTransaction(javax.json.JsonObject json)
    {
        List<String> requiredParameters = new ArrayList<>();
        requiredParameters.add("user_id");
        requiredParameters.add("account_id");

        validation valid = new validation(json, requiredParameters);


        JsonObject value = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(AccountTransactionsEntity.class, new AccountTransactionAdaptor()).create();
        String output = null;
        String userId = json.getString("user_id");
        AccountManagerImp acct = new AccountManagerImp();
        Boolean isAcctBelongToUser = acct.verifyAccountBelongsToUser(Integer.parseInt(json.getString("account_id")), userId);

        if(!valid.doValidation())
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Invalid list of parameters, "+valid.getFailedParameters());
            output = value.toString();
        }
        else if(userId.length() != 8 || !isAcctBelongToUser)
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Could not validate user");
            output = value.toString();
        }
        else
        {
            List<AccountTransactionsEntity> transactions = ((json.containsKey("budget")) ?
                    acct.getAccountTransactions(Integer.parseInt(json.getString("account_id")), Integer.parseInt(json.getString("budget"))) :
                    acct.getAccountTransactions(Integer.parseInt(json.getString("account_id"))));
            AccountBalanceEntity balanceEntity = acct.getUserAccountBalance(Integer.parseInt(json.getString("account_id")));

            if(transactions == null || transactions.isEmpty()) {
                value.addProperty("status", "success");
                value.addProperty("balance", balanceEntity.getBalance());
                value.addProperty("last_balance", balanceEntity.getLastBalance());
                value.addProperty("currency", balanceEntity.getBankAccount().getCurrencies().getCode());

                output = value.toString();
            }
            else
            {

                value.addProperty("status", "success");
                value.addProperty("balance", balanceEntity.getBalance());
                value.addProperty("last_balance", balanceEntity.getLastBalance());
                value.addProperty("currency", balanceEntity.getBankAccount().getCurrencies().getCode());
                value.add("list", gson.toJsonTree(transactions));
                output = value.toString();
            }

        }

        return output;
    }



    @POST
    @Path("/transaction/budget")
    @Produces({"application/json"})
    public String getSumOfBudgetTransaction(javax.json.JsonObject json)
    {
        List<String> requiredParameters = new ArrayList<>();
        requiredParameters.add("user_id");
        requiredParameters.add("budget_id");

        validation valid = new validation(json, requiredParameters);


        JsonObject value = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(AccountTransactionsEntity.class, new AccountTransactionAdaptor()).create();
        String output = null;
        String userId = json.getString("user_id");
        AccountManagerImp acct = new AccountManagerImp();


        if(!valid.doValidation())
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Invalid list of parameters, "+valid.getFailedParameters());
            output = value.toString();
        }
        else if(userId.length() != 8 )
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Could not validate user");
            output = value.toString();
        }
        else
        {
            List<AccountTransactionsEntity> transactions = acct.getAccountBudgetTransactions(json.getInt("budget_id"));


            if(transactions == null || transactions.isEmpty()) {
                value.addProperty("status", "success");
                value.addProperty("sum", 0);
                output = value.toString();
            }
            else
            {

                value.addProperty("status", "success");
                int sum = 0;
                for(AccountTransactionsEntity transactionsEntity: transactions)
                {
                    sum = sum + (Integer.parseInt(transactionsEntity.getTransactionAmount()));
                }

                value.addProperty("sum",sum);

                output = value.toString();
            }

        }

        return output;
    }



    @POST
    @Path("/budgets")
    @Produces({"application/json"})
    public String getAllBudgets(javax.json.JsonObject json)
    {
        List<String> requiredParameters = new ArrayList<>();
        requiredParameters.add("user_id");

        validation valid = new validation(json, requiredParameters);


        JsonObject value = new JsonObject();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(BudgetsEntity.class, new BudgetAdaptor()).create();
        String output = null;
        String userId = json.getString("user_id");
        AccountManagerImp acct = new AccountManagerImp();


        if(!valid.doValidation())
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Invalid list of parameters, "+valid.getFailedParameters());
            output = value.toString();
        }
        else if(userId.length() != 8 )
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Could not validate user");
            output = value.toString();
        }
        else
        {
            List<BudgetsEntity> budgets = acct.getBudgets(userId);


            if(budgets == null || budgets.isEmpty()) {
                value.addProperty("status", "success");
                value.addProperty("message","no budget found for this user");

                output = value.toString();
            }
            else
            {
                value.addProperty("status", "success");
                value.add("list", gson.toJsonTree(budgets));
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
        List<String> requiredParameters = new ArrayList<>();
        requiredParameters.add("user_id");
        requiredParameters.add("account_name");
        requiredParameters.add("account_number");
        requiredParameters.add("start_balance");
        requiredParameters.add("currency_code");
        requiredParameters.add("currency_country");

        validation valid = new validation(json, requiredParameters);

        JsonObject value = new JsonObject();


        if(!valid.doValidation())
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Invalid list of parameters, "+valid.getFailedParameters());
        }else {

            // Accounts Component
            AccountManagerImp ami = new AccountManagerImp();


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


            if (ami.createNewAccount(bae, abe, ce)) {
                value.addProperty("status", "success");
                value.addProperty("message", "account created");
            } else {
                value.addProperty("status", "error");
                value.addProperty("error", "could not create new account");
            }
        }

        return value.toString();
    }

    @POST
    @Path("/create/budget")
    @Produces({"application/json"})
    public String createNewBudget(javax.json.JsonObject json)
    {
        List<String> requiredParameters = new ArrayList<>();
        requiredParameters.add("user_id");
        requiredParameters.add("account_id");
        requiredParameters.add("budget_name");
        requiredParameters.add("budget_amount");
        requiredParameters.add("budget_description");


        validation valid = new validation(json, requiredParameters);

        JsonObject value = new JsonObject();

        if(!valid.doValidation())
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Invalid list of parameters, "+valid.getFailedParameters());
        }
        else
        {
            // Accounts Component
            AccountManagerImp ami = new AccountManagerImp();


            BudgetsEntity bet = new BudgetsEntity();
            bet.setBudgetName(json.getString("budget_name"));
            bet.setBudgetMaxAmount(Long.parseLong(json.getString("budget_amount")));
            bet.setBudgetDescription(json.getString("budget_description"));
            bet.setUsers(new UsersEntity(json.getString("user_id")));
            BankAccountEntity ba = new BankAccountEntity();
            ba.setId(Integer.parseInt(json.getString("account_id")));
            bet.setBankAccount(ba);


            if(ami.createNewBudget(bet))
            {
                value.addProperty("status", "success");
                value.addProperty("message", "budget created");
            }
            else {
                value.addProperty("status", "error");
                value.addProperty("error", "could not create new budget");
            }
        }

        return value.toString();
    }

    @POST
    @Path("/delete")
    @Produces({"application/json"})
    public String deleteBankAccount(javax.json.JsonObject json)
    {

        List<String> requiredParameters = new ArrayList<>();
        requiredParameters.add("user_id");
        requiredParameters.add("account_id");


        validation valid = new validation(json, requiredParameters);

        JsonObject value = new JsonObject();

        if(!valid.doValidation())
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Invalid list of parameters, "+valid.getFailedParameters());
        }
        else
        {
            // Accounts Component
            AccountManagerImp ami = new AccountManagerImp();

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

        }

        return value.toString();
    }

    @POST
    @Path("/transaction/add")
    @Produces({"application/json"})
    public String addTransaction(javax.json.JsonObject json)
    {
        List<String> requiredParameters = new ArrayList<>();
        requiredParameters.add("user_id");
        requiredParameters.add("account_id");
        requiredParameters.add("transaction_type");
        requiredParameters.add("trx_amount");
        requiredParameters.add("trx_name");
        requiredParameters.add("trx_date");


        validation valid = new validation(json, requiredParameters);

        JsonObject value = new JsonObject();
        String output = null;

        if(!valid.doValidation())
        {
            value.addProperty("status", "error");
            value.addProperty("error", "Invalid list of parameters, "+valid.getFailedParameters());
            output = value.toString();
        }
        else
        {
            AccountManagerImp acct = new AccountManagerImp();
            String userId = json.getString("user_id");

            if(userId.length() != 8 || !acct.verifyAccountBelongsToUser(Integer.parseInt(json.getString("account_id")), userId))
            {
                value.addProperty("status", "error");
                value.addProperty("error", "Could not validate user");
                output = value.toString();
            }else
            {

                TransactionCategoryEntity tce = acct.getCategory(json.getString("type"));

                if(tce == null)
                {
                    value.addProperty("status", "error");
                    value.addProperty("error", "Internal error, could not process request");
                    output = value.toString();
                }
                else
                {
                    AccountTransactionsEntity ate = new AccountTransactionsEntity();
                    if(json.getString("transaction_type").equals("budget"))
                    {
                        BudgetsEntity bet = new BudgetsEntity();
                        bet.setBudgetId(Integer.parseInt(json.getString("budget_id")));
                        ate.setBudgetEnty(bet);
                    }
                    BankAccountEntity account = new BankAccountEntity();
                    account.setId(Integer.parseInt(json.getString("account_id")));
                    ate.setBankAccount(account);
                    ate.setTransactionAmount(json.getString("trx_amount"));
                    ate.setTransactionCategory(tce);
                    ate.setTransactionName(json.getString("trx_name"));
                    ate.setTransactionDate(json.getString("trx_date"));

                    if(acct.addNewTransaction(ate))
                    {
                        value.addProperty("status", "success");
                        value.addProperty("message", "Transaction added with id: "+ate.getTransactionId());
                        output = value.toString();
                    }
                    else
                    {
                        value.addProperty("status", "error");
                        value.addProperty("error", "Internal error, failed to add transaction");
                        output = value.toString();
                    }
                }

            }
        }


        return output;
    }




}

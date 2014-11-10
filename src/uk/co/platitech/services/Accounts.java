package uk.co.platitech.services;

import com.google.gson.Gson;
import uk.co.platitech.components.accountmanager.v1.AccountManagerImp;
import uk.co.platitech.components.appauthentication.v1.AppAuthenticationImp;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by samuel on 10/11/14.
 */
@Stateless
@Path("co.uk.platitech.budget.accounts")
public class Accounts {

    @GET
    @Path("{userId}")
    @Produces({"application/json"})
    public JsonObject getAccounts(@PathParam("userId") String userId)
    {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = null;
        Gson js = new Gson();
        AccountManagerImp acct = new AccountManagerImp();

        if(userId.length() != 8)
        {
            value = factory.createObjectBuilder().add("status", "error").add("error", "could not validate user id provided").build();
        }
        else
        {
            List accounts = acct.getUserBankAccounts(userId);
            value = factory.createObjectBuilder().add("status", "success").add("accounts", ((accounts == null) ? "" : js.toJson(accounts))).build();
        }

        return value;
    }
}

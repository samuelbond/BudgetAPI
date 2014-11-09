package uk.co.platitech.services;

import uk.co.platitech.BankAccountEntity;
import uk.co.platitech.UsersEntity;
import uk.co.platitech.components.usermanager.v1.UserManagerImp;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.*;

/**
 * Created by Samuel on 11/8/2014.
 */
@Stateless
@Path("co.uk.platitech.budget.users")
public class Users {

    @POST
    @Path("/register")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public JsonObject register(UsersEntity usr)
    {
        UserManagerImp uMang = new UserManagerImp();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = null;

        if(uMang.createNewUser(usr.getFullname(), usr.getEmail(), usr.getPassword()))
        {
            value = factory.createObjectBuilder().add("status", "success").add("message", "user account created successfully").build();
        }
        else
        {
            value = factory.createObjectBuilder().add("status", "error").add("error", "could not create new user account").build();
        }
        return value;
    }

    @POST
    @Path("/login")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public JsonObject login(JsonObject json)
    {
        BankAccountEntity bc = new BankAccountEntity();

        UserManagerImp uMang = new UserManagerImp();
        UsersEntity usr = uMang.loginUser(json.getString("email"), json.getString("password"));
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = null;
        if(usr != null)
        {
            value = factory.createObjectBuilder().add("status", "success")
                    .add("userId", usr.getUserId())
                    .add("fullname", usr.getFullname())
                    .add("email", usr.getEmail())
                    .build();
        }
        else
        {
            value = factory.createObjectBuilder().add("status", "error").add("error", "failed to login user with email: "+json.get("email").toString()).build();
        }

        return value;
    }
}

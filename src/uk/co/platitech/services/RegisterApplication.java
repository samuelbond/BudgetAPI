package uk.co.platitech.services;

import uk.co.platitech.components.appauthentication.v1.AppAuthenticationImp;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by Samuel on 11/8/2014.
 */

@Stateless
@Path("co.uk.platitech.budget.registerapplication")
public class RegisterApplication {

    @GET
    @Path("/register/{appId}")
    @Produces({"application/json"})
    public JsonObject register(@PathParam("appId") String appId)
    {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = null;
        AppAuthenticationImp auth = new AppAuthenticationImp();
        String key = auth.registerApplication(appId);
        if(key.equals(""))
        {
            value = factory.createObjectBuilder().add("status", "error").add("error", "could not register application").build();
        }
        else
        {
            value = factory.createObjectBuilder().add("status", "success").add("key", key).build();
        }

        return value;
    }


    @GET
    @Path("{key}")
    @Produces({"application/json"})
    public JsonObject authenticate(@PathParam("key") String key)
    {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonObject value = null;
        AppAuthenticationImp auth = new AppAuthenticationImp();
        if(auth.authenticateApplication(key))
        {
            value = factory.createObjectBuilder().add("status", "success").add("authentication", "successful").build();
        }
        else
        {
            value = factory.createObjectBuilder().add("status", "error").add("error", "could not authenticate application with key"+key).build();
        }

        return value;
    }
}


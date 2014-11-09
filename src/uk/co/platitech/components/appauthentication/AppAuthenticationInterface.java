package uk.co.platitech.components.appauthentication;

/**
 * Created by Samuel on 11/8/2014.
 */
public interface AppAuthenticationInterface {
    /**
     * Registers a new app and returns a key for the app
     * @param appId
     * @return String App Key
     */
    public String registerApplication(String appId);
}

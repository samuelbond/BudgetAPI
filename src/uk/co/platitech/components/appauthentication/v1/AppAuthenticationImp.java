package uk.co.platitech.components.appauthentication.v1;

import uk.co.platitech.AppsEntity;
import uk.co.platitech.components.appauthentication.AppAuthenticationInterface;
import uk.co.platitech.helpers.HashGenerator;
import uk.co.platitech.models.DataRepository;

/**
 * Created by Samuel on 11/8/2014.
 */
public class AppAuthenticationImp implements AppAuthenticationInterface {

    protected DataRepository data;
    protected HashGenerator hash;
    protected enum  applicationId {
        LENGHT(5);
        private int value;

        private applicationId(int value)
        {
            this.value = value;
        }

        public int length()
        {
            return this.value;
        }
    }


    public AppAuthenticationImp() {
        this.data = new DataRepository();
    }

    public boolean authenticateApplication(String key)
    {
        AppsEntity app = this.data.getAppByKey(key);

        if(app != null && app.getAppId() != null && app.getAppKey().equals(key))
        {
            return true;
        }

        return false;
    }


    /**
     * Registers a new app and returns a key for the app
     * @param appId
     * @return String App Key
     */
    @Override
    public String registerApplication(String appId)
    {
        AppsEntity app = null;
        String key = "";

        if(applicationId.LENGHT.length() == appId.length())
        {
            this.hash = new HashGenerator(appId);
            key = this.hash.generateHash();
            app = new AppsEntity();
            app.setAppId(appId);
            app.setAppKey(key);
            if(!this.data.insert(app))
            {
                key = "";
            }
        }

        return  key;
    }
}

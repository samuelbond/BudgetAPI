package uk.co.platitech.components.usermanager;

import uk.co.platitech.UsersEntity;

/**
 * Created by Samuel on 11/8/2014.
 */
public interface UserManagerInterface {

    public boolean createNewUser(String name, String email,String password);

    public UsersEntity getUserDetails(String userId);

    public UsersEntity loginUser(String email, String password);
}

package uk.co.platitech.components.usermanager.v1;

import uk.co.platitech.UsersEntity;
import uk.co.platitech.components.usermanager.UserManagerInterface;
import uk.co.platitech.helpers.EmailValidator;
import uk.co.platitech.helpers.HashGenerator;
import uk.co.platitech.helpers.RandomStringGenerator;
import uk.co.platitech.models.DataRepository;

/**
 * Created by Samuel on 11/8/2014.
 */
public class UserManagerImp implements UserManagerInterface {

    private DataRepository data;

    public UserManagerImp() {
        this.data = new DataRepository();
    }

    @Override
    public boolean createNewUser(String name, String email, String password) {
        EmailValidator em = new EmailValidator();
        if(em.validate(email) && name.length() > 2 && password.length() > 3)
        {
            UsersEntity usr = new UsersEntity();
            HashGenerator gen = new HashGenerator(password);
            usr.setEmail(email);
            usr.setFullname(name);
            usr.setPassword(gen.generateHash());
            usr.setUserId((new RandomStringGenerator(8).generate()));

            if(this.data.insert(usr))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public UsersEntity getUserDetails(String userId) {

        if(userId.length() == 8)
        {
            UsersEntity usr = this.data.getUserByUserId(userId);
            if(usr != null)
            {
                return  usr;
            }
        }
        return null;
    }

    @Override
    public UsersEntity loginUser(String email, String password) {
        EmailValidator em = new EmailValidator();
        if(em.validate(email))
        {
            HashGenerator gen = new HashGenerator(password);
            String hashedPassword = gen.generateHash();
            UsersEntity usr = this.data.getUserByEmailAndPassword(email, hashedPassword);
            if(usr != null)
            {
                return usr;
            }
        }
        return null;
    }
}

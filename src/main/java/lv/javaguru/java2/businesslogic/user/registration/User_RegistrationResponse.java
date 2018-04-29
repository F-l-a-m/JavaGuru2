package lv.javaguru.java2.businesslogic.user.registration;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.domain.User;

import java.util.List;

public class User_RegistrationResponse {
    
    private User user;
    private boolean success;
    private List<Error> errors;
    
    public User_RegistrationResponse( User user ) {
        this.user = user;
        this.success = true;
        this.errors = null;
    }
    
    public User_RegistrationResponse( List<Error> errors ) {
        this.user = null;
        this.success = false;
        this.errors = errors;
    }
    
    public User getUser( ) {
        return user;
    }
    
    public void setUser( User user ) {
        this.user = user;
    }
    
    public boolean isSuccess( ) {
        return success;
    }
    
    public void setSuccess( boolean success ) {
        this.success = success;
    }
    
    public List<Error> getErrors( ) {
        return errors;
    }
    
    public void setErrors( List<Error> errors ) {
        this.errors = errors;
    }
}

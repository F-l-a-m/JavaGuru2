package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.domain.User;

import java.util.List;

public class User_AddResponse {

    private User user;
    private List<Error> errors;
    private boolean success;
    
    public User_AddResponse( User user, List<Error> errors, boolean success ) {
        this.user = user;
        this.errors = errors;
        this.success = success;
    }
    
    public User getUser( ) {
        return user;
    }
    
    public void setUser( User user ) {
        this.user = user;
    }
    
    public List<Error> getErrors( ) {
        return errors;
    }
    
    public void setErrors( List<Error> errors ) {
        this.errors = errors;
    }
    
    public boolean isSuccess( ) {
        return success;
    }
    
    public void setSuccess( boolean success ) {
        this.success = success;
    }
}

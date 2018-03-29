package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.domain.User;

import java.util.List;

public class InitializeUserResponse {
    
    private User user;
    private List<Error> errors;
    private boolean isSuccess;
    
    public InitializeUserResponse( User user, List<Error> errors, boolean isSuccess ) {
        this.user = user;
        this.errors = errors;
        this.isSuccess = isSuccess;
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
        return isSuccess;
    }
    
    public void setSuccess( boolean success ) {
        isSuccess = success;
    }
}

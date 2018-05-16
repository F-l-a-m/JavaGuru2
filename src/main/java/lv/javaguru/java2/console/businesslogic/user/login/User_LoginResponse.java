package lv.javaguru.java2.console.businesslogic.user.login;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.domain.User;

import java.util.List;

public class User_LoginResponse {
    
    private User user;
    private boolean success;
    private List<Error> errors;
    
    public User_LoginResponse( User user ) {
        this.user = user;
        this.success = true;
        this.errors = null;
    }
    
    public User_LoginResponse( List<Error> errors ) {
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

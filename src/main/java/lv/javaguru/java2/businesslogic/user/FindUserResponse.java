package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.domain.User;

public class FindUserResponse {

    private User user;
    private boolean success;
    
    public FindUserResponse( User user, boolean success ) {
        this.user = user;
        this.success = success;
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
}

package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.businesslogic.Error;

import java.util.List;

public class User_FindInRoomResponse {
    private List<Error> errors;
    private boolean success;
    
    public User_FindInRoomResponse( List<Error> errors, boolean success ) {
        this.errors = errors;
        this.success = success;
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

package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.domain.Room;

import java.util.List;

public class Room_JoinOrCreateResponse {
    
    Room room;
    List<Error> errors;
    List<String> info;
    boolean isSuccess;
    
    public Room_JoinOrCreateResponse( Room room, List<Error> errors, boolean isSuccess ) {
        this.room = room;
        this.errors = errors;
        this.isSuccess = isSuccess;
    }
    
    public Room getRoom( ) {
        return room;
    }
    
    public void setRoom( Room room ) {
        this.room = room;
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
    
    public List<String> getInfo( ) {
        return info;
    }
    
    public void setInfo( List<String> info ) {
        this.info = info;
    }
}

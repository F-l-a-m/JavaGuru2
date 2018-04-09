package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.domain.Room;

public class Room_FindResponse {
    
    private Room room;
    private boolean success;
    
    public Room_FindResponse( Room room, boolean success ) {
        this.room = room;
        this.success = success;
    }
    
    public Room getRoom( ) {
        return room;
    }
    
    public void setRoom( Room room ) {
        this.room = room;
    }
    
    public boolean isSuccess( ) {
        return success;
    }
    
    public void setSuccess( boolean success ) {
        this.success = success;
    }
}

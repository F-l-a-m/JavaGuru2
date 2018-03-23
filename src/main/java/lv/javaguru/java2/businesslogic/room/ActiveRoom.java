package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.domain.ChatRoom;

public class ActiveRoom {
    
    ChatRoom room;
    
    public ChatRoom getRoom( ) {
        return room;
    }
    
    public void setRoom( ChatRoom room ) {
        this.room = room;
    }
}

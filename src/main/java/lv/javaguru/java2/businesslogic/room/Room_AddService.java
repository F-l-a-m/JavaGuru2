package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Room_AddService {
    
    @Autowired private Database database;
    
    @Transactional
    public Room_AddResponse addRoom( String roomName, String creatorNickname ) {
        
        Room room = database.chatRoom_add( roomName, creatorNickname );
    
        if ( room != null )
            return new Room_AddResponse( room, true );
        return new Room_AddResponse( null, false );
    }
}

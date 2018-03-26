package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindRoomService {
    
    @Autowired private Database database;
    
    public FindRoomResponse findRoomByName( String roomName ) {
        
        Optional<Room> room = database.findChatRoomByRoomName( roomName );
        
        if ( room.isPresent( ) ) {
            return new FindRoomResponse( room.get( ), true );
        } else
            return new FindRoomResponse( null, false );
    }
}

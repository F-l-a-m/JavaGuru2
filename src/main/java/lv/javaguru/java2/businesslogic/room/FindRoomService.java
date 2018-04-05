package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class FindRoomService {
    
    @Autowired private Database database;
    
    @Transactional
    public FindRoomResponse findRoomByName( String roomName ) {
        
        Optional<Room> search = database.chatRoom_get( roomName );
    
        return search.map( room -> new FindRoomResponse( room, true ) )
                .orElseGet( ( ) -> new FindRoomResponse( null, false ) );
    }
}

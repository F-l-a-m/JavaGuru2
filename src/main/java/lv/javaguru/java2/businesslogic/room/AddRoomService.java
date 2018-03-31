package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AddRoomService {
    
    @Autowired private Database database;
    
    @Transactional
    public AddRoomResponse addRoom( String roomName, String creatorNickname ) {
        
        Optional<Room> roomOpt = database.createNewChatRoom( roomName, creatorNickname );
    
        return roomOpt.map( room -> new AddRoomResponse( room, true ) )
                .orElseGet( ( ) -> new AddRoomResponse( null, false ) );
    }
}

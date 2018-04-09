package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class Room_ListAllService {
    
    @Autowired private Database database;
    
    @Transactional
    public Room_ListAllResponse list( ) {
        List<String> listOfAllRooms = new ArrayList<>( );
        @SuppressWarnings (value="unchecked")
        List<Room> roomList = database.chatRoom_getAllRooms( );
        roomList.forEach( x -> listOfAllRooms.add( x.getName( ) ) );
        if ( !listOfAllRooms.isEmpty( ) )
            return new Room_ListAllResponse( listOfAllRooms, true );
        return new Room_ListAllResponse( null, false );
    }
}

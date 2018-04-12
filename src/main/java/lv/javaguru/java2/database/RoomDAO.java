package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDAO {
    
    Room add( String roomName, String creatorNickname );
    
    Optional<Room> get( Long roomId );
    
    Optional<Room> get( String roomName );
    
    List getAllRooms( );
    
}

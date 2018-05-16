package lv.javaguru.java2.console.database;

import lv.javaguru.java2.console.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    
    void save( Room room );
    
    Optional<Room> get( String roomName );
    
    List<Room> getAllRooms( );
    
}

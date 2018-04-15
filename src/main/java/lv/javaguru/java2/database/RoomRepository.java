package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    
    void save( Room room );
    
    Optional<Room> get( String roomName );
    
    List<Room> getAllRooms( );
    
}

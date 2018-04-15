package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class Room_GetAllService {
    
    @Autowired private RoomRepository roomRepository;
    
    @Transactional
    public Room_GetAllResponse getRoomList( ) {
        @SuppressWarnings(value = "unchecked")
        List<Room> roomList = roomRepository.getAllRooms( );
        if ( !roomList.isEmpty( ) )
            return new Room_GetAllResponse( roomList, true );
        return new Room_GetAllResponse( null, false );
    }
    
    @Transactional
    public Room_GetAllResponse getStringList( ) {
        List<String> listOfAllRooms = new ArrayList<>( );
        @SuppressWarnings(value = "unchecked")
        List<Room> roomList = roomRepository.getAllRooms( );
        if ( !roomList.isEmpty( ) ) {
            roomList.forEach( room -> listOfAllRooms.add( room.getName( ) ) );
            return new Room_GetAllResponse( listOfAllRooms, true );
        }
        return new Room_GetAllResponse( null, false );
    }
}

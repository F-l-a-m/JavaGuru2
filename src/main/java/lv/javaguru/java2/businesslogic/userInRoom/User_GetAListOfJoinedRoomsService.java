package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class User_GetAListOfJoinedRoomsService {
    
    @Autowired UserInRoomRepository userInRoomRepository;
    
    @Transactional
    public List<Room> getList( User user ) {
        List<Room> roomList = userInRoomRepository.getAListOfJoinedRooms( user );
        if ( !roomList.isEmpty( ) ) {
            return roomList;
        }
        return null;
    }
    
    @Transactional
    public List<String> getAStringListOfJoinedRooms( User user ) {
        List<Room> roomList = userInRoomRepository.getAListOfJoinedRooms( user );
        if ( !roomList.isEmpty( ) ) {
            List<String> stringList = new ArrayList<>( );
            roomList.forEach( room -> stringList.add( room.getName( ) ) );
            return stringList;
        }
        return null;
    }
}

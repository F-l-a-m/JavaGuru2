package lv.javaguru.java2.businesslogic.userToRoom;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class User_GetAListOfJoinedRoomsService {
    
    @Autowired Database database;
    
    @Transactional
    public List<Room> getList( User user ) {
        List<Room> roomList = database.userInRoom_getAListOfJoinedRooms( user.getId( ) );
        if ( !roomList.isEmpty( ) ) {
            return roomList;
        }
        return null;
    }
    
    @Transactional
    public List<String> getAStringListOfJoinedRooms( User user ) {
        List<Room> roomList = database.userInRoom_getAListOfJoinedRooms( user.getId( ) );
        if ( !roomList.isEmpty( ) ) {
            List<String> stringList = new ArrayList<>( );
            roomList.forEach( room -> stringList.add( room.getName( ) ) );
            return stringList;
        }
        return null;
    }
}

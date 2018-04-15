package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserInRoom;

import java.util.List;

public interface UserInRoomRepository {
    
    void addUserToRoom( UserInRoom userInRoom );
    
    boolean findUserInRoom( Long userId, Long roomId );
    
    boolean removeUserFromRoom( Long userId, Long roomId );
    
    List<Room> getAListOfJoinedRooms( User user );
    
}

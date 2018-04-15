package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserInRoom;

import java.util.List;

public interface UserInRoomRepository {
    
    void addUserToRoom( User user, Room room );
    
    boolean findUserInRoom( User user, Room room );
    
    void removeUserFromRoom( User user, Room room );
    
    List<Room> getAListOfJoinedRooms( User user );
    
}

package lv.javaguru.java2.console.database;

import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import lv.javaguru.java2.console.domain.UserInRoom;

import java.util.List;

public interface UserInRoomRepository {
    
    void addUserToRoom( User user, Room room );
    
    boolean findUserInRoom( User user, Room room );
    
    void removeUserFromRoom( User user, Room room );
    
    List<Room> getAListOfJoinedRooms( User user );
    
}

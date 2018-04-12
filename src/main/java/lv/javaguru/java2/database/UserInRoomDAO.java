package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Room;

import java.util.List;

public interface UserInRoomDAO {
    
    void addUserToRoom( Long userId, Long roomId );
    
    boolean findUserInRoom( Long userId, Long roomId );
    
    boolean removeUserFromRoom( Long userId, Long roomId );
    
    List<Room> getAListOfJoinedRooms( Long userId );
    
}

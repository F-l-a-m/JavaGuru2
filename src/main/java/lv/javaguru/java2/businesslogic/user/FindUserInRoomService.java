package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FindUserInRoomService {
    
    @Autowired private Database database;
    
    @Transactional
    public boolean findUserInRoom( Long userId, Long roomId ) {
        return database.userInRoom_findUserInRoom( userId, roomId );
    }
}

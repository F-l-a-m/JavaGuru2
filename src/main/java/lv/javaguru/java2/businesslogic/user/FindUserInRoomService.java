package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FindUserInRoomService {
    
    @Autowired private Database database;
    
    @Transactional
    public boolean findUserInRoom( Long userId, String roomName ) {
        return database.findUserInRoom( userId, roomName );
    }
}

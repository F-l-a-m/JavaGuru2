package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddUserToRoomService {
    
    @Autowired private Database database;
    
    @Transactional
    public void add( User user, Room room ) {
        database.addUserToRoom( user.getId( ), room.getId( ) );
    }
}

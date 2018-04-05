package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetAListOfJoinedRoomsService {
    
    @Autowired Database database;
    
    @Transactional
    public List<Room> getList( User user ) {
        database.userInRoom_getAListOfJoinedRooms( user.getId( ) );
        return null;
    }
}

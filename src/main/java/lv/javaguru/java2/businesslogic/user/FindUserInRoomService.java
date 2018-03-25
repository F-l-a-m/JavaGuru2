package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindUserInRoomService {
    @Autowired private Database database;

    public boolean findUserInRoom(Long userId, String roomName) {
        return database.findUserInRoomById(userId, roomName);
    }
}

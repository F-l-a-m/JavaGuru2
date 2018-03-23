package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddRoomService {

    @Autowired private Database database;

    public AddRoomResponse addRoom(String roomName, String creatorNickname) {
        Optional<Room> room = database.createNewChatRoom(roomName, creatorNickname);
        return new AddRoomResponse(true, room.get(), null);
    }
}

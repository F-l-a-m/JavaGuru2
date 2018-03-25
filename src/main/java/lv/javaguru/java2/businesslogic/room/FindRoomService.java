package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FindRoomService {

    @Autowired private Database database;
    @Autowired private RoomNameValidator validator;

    public FindRoomResponse findRoomByName(String roomName) {
        List<Error> errors = validator.validate(roomName);
        if( errors.isEmpty() ) {
            Optional<Room> search = database.findChatRoomByRoomName(roomName);
            if (search.isPresent()) {
                return new FindRoomResponse(search.get(), null, true);
            } else {
                errors.add(new Error("Room with name " + roomName + " not found"));
                return new FindRoomResponse(null, errors, false);
            }
        } else {
            return new FindRoomResponse(null, errors, false);
        }
    }
}

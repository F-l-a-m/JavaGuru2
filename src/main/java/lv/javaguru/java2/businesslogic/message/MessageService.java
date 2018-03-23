/*
package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.database.Database;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MessageService {

    private final Database database;

    public MessageService(Database database) {
        this.database = database;
    }

    public Message saveMessageToDatabase(String message, User user, Room room) {
        MyTimestamp myTimestamp = new MyTimestamp();
        return database.addChatMessage(message, user.getNickname(), room.getId());
    }

    public Message getLastChatMessageInARoom(Room room) {
        Optional<Message> msg = database.getLastChatMessageInRoom(room.getId());
        if(msg.isPresent()) {
            return msg.get();
        }
        else {
            System.out.println("No messages in room " + room.getName());
            return null;
        }
    }

    public List<Message> getAllChatHistoryInRoom(String roomName) {
        // Check if room with given name exists
        Optional<Room> foundRoom = database.findChatRoomByRoomName(roomName);
        if(foundRoom.isPresent()) {
            return database.getAllChatHistoryInRoom(foundRoom.get().getId());
        }
        return null;
    }
}
*/

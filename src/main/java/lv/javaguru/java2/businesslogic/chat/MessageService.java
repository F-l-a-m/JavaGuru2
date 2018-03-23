package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.domain.ChatRoom;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.database.Database;

import java.util.List;
import java.util.Optional;

public class MessageService {

    private final Database database;

    public MessageService(Database database) {
        this.database = database;
    }

    public Message saveMessageToDatabase(String message, User user, ChatRoom room) {
        MyTimestamp myTimestamp = new MyTimestamp();
        return database.addChatMessage(message, user.getNickname(), room.getId());
    }

    public Message getLastChatMessageInARoom(ChatRoom room) {
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
        Optional<ChatRoom> foundRoom = database.findChatRoomByRoomName(roomName);
        if(foundRoom.isPresent()) {
            return database.getAllChatHistoryInRoom(foundRoom.get().getId());
        }
        return null;
    }
}

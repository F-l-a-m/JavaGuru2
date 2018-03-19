package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageService {

    private Database database;

    public MessageService(Database database) {
        this.database = database;
    }

    public void saveMessageToDatabase(String message, User user, ChatRoom room) {
        Timestamp timestamp = new Timestamp();
        Message msg = new Message(
                timestamp.getTimestamp(),
                user.getNickname(),
                message,
                room.getId()
        );
        database.addChatMessage(msg);
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

    public List<Message> getAllChatHistoryInRoom(ChatRoom room) {
        // Check if room with given id exists
        Optional<ChatRoom> foundRoom = database.findChatRoomByRoomId(room.getId());
        if(foundRoom.isPresent()) {
            return database.getAllChatHistoryInRoom(room.getId());
        }
        return null;
    }
}
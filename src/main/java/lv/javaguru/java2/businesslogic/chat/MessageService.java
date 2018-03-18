package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

import java.util.Optional;

public class MessageService {

    private Database database;

    public MessageService(Database database) {
        this.database = database;
    }

    public void saveMessageToDatabase(String message, User user, ChatRoom room){
        Timestamp timestamp = new Timestamp();
        Message msg = new Message(
                timestamp.getTimestamp(),
                message,
                user.getId(),
                room.getId()
        );
        database.addChatMessage(msg);
    }

    public Message getLastChatMessageInARoom(ChatRoom room){
        Optional<Message> msg = database.getLastChatMessageInRoom(room.getId());
        if(msg.isPresent()){
            return msg.get();
        }
        else {
            System.out.println("No messages in room " + room.getName());
            return null;
        }
    }
}

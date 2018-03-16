package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.chat.Message;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.user.User;
import java.util.List;
import java.util.Optional;

public interface Database {

    void addNewUser(User user);

    Optional<User> getLastUser();

    void addChatLine(Message message);

    List<Message> getAllChat();

    Message getLastChatMessage();
    void addToRoomList(ChatRoom newRoom);

    void removeFormRoomList(ChatRoom newRoom);

    List<ChatRoom> getRoomList();

    Optional<ChatRoom> findChatRoom(String name);
}

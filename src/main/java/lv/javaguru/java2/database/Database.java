package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.chat.Message;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.user.User;
import java.util.List;
import java.util.Optional;

public interface Database {

    // User management
    void addNewUser(User user);
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByNickname(String nickname);
    void addUserToRoom(Long userId, Long roomId);
    void removeUserFromRoom(Long userId, String roomName);
    boolean findUserInARoom(Long userId, String roomName);


    // Chat room management
    void createNewChatRoom(String roomName);
    Optional<ChatRoom> findChatRoom(String roomName);
    List<ChatRoom> getListOfAllRooms();


    // Message management
    void addChatMessage(Message message);
    Optional<Message> getLastChatMessageInRoom(String roomName);
    List<Message> getAllChatHistoryInRoom(String roomName);
}

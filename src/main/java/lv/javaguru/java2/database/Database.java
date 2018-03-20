package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.chat.Message;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.user.User;
import java.util.List;
import java.util.Optional;

public interface Database {

    // User management
    User addNewUser(User user);
    void updateUserActiveStatus(User user, boolean activeStatus);
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByNickname(String nickname);
    void changeUserNickname(Long userId, String nickname);
    
    // user in room management
    void addUserToRoom(Long userId, Long roomId);
    void removeUserFromRoom(Long userId, Long roomId);
    boolean findUserInRoomById(Long userId, String roomName);
    //boolean findUserInRoomByNickname(String nickname, String roomName);


    // Chat room management
    Optional<ChatRoom> createNewChatRoom(String roomName);
    Optional<ChatRoom> findChatRoomByRoomId(Long roomId);
    Optional<ChatRoom> findChatRoomByRoomName(String roomName);
    List<ChatRoom> getListOfAllRooms();


    // Message management
    void addChatMessage(Message message);
    Optional<Message> getLastChatMessageInRoom(Long roomId);
    List<Message> getAllChatHistoryInRoom(Long roomId);
}

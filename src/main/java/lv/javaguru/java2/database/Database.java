package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import java.util.List;
import java.util.Optional;

public interface Database {

    // User management
    User addNewGuest(String nickname);
    void updateUserActiveStatus(User user, boolean activeStatus);
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByNickname(String nickname);
    void changeUserNickname(String oldNickname, String newNickname);
    
    // user in room management
    void addUserToRoom(Long userId, Long roomId);
    void removeUserFromRoom(Long userId, Long roomId);
    boolean findUserInRoomById(Long userId, String roomName);
    //boolean findUserInRoomByNickname(String nickname, String roomName);


    // Chat room management
    Optional<Room> createNewChatRoom(String roomName, String creatorNickname);
    Optional<Room> findChatRoomByRoomId(Long roomId);
    Optional<Room> findChatRoomByRoomName(String roomName);
    List<Room> getListOfAllRooms();


    // Message management
    Message addChatMessage(String message, String nickname, Long roomId);
    Optional<Message> getLastChatMessageInRoom(Long roomId);
    List<Message> getAllChatHistoryInRoom(Long roomId);
}

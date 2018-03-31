package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {
    
    
    // User management
    User addUser( String nickname );
    void updateUserActiveStatus( User user, boolean activeStatus );
    Optional<User> findUser( Long userId );
    Optional<User> findUser( String nickname );
    void changeUserNickname( String oldNickname, String newNickname );
    
    
    // user in room management
    void addUserToRoom( Long userId, Long roomId );
    void removeUserFromRoom( Long userId, Long roomId );
    boolean findUserInRoom( Long userId, String roomName );
    
    
    // Chat room management
    Optional<Room> createNewChatRoom( String roomName, String creatorNickname );
    Optional<Room> findChatRoom( Long roomId );
    Optional<Room> findChatRoom( String roomName );
    List getListOfAllRooms( );
    
    
    // Message management
    Message addChatMessage( String message, String nickname, Long roomId );
    List getAllChatHistoryInRoom( Long roomId );
}

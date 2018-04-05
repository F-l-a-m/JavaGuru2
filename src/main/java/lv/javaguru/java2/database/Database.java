package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;

import java.util.List;
import java.util.Optional;

public interface Database {
    
    
    // User management
    User user_add( String nickname );
    void user_updateActiveStatus( User user, boolean activeStatus );
    Optional<User> user_get( Long userId );
    Optional<User> user_get( String nickname );
    void user_changeNickname( String oldNickname, String newNickname );
    void user_changeNickname( User user, String newNickname );
    
    // Chat room management
    Room chatRoom_add( String roomName, String creatorNickname );
    Optional<Room> chatRoom_get( Long roomId );
    Optional<Room> chatRoom_get( String roomName );
    List chatRoom_getAllRooms( );
    
    // user in room management
    void userInRoom_addUserToRoom( Long userId, Long roomId );
    void userInRoom_removeUserFromRoom( Long userId, Long roomId );
    boolean userInRoom_findUserInRoom( Long userId, Long roomId);
    List<Room> userInRoom_getAListOfJoinedRooms( Long userId );
    
    // Message management
    Message message_add( String message, String nickname, Long roomId );
    List message_getAllMessages( Long roomId );
}

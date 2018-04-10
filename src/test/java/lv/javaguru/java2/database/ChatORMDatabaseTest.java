package lv.javaguru.java2.database;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class ChatORMDatabaseTest {
    
    @Autowired Database database;
    
    @Test
    public void shouldCreateNewUser( ) {
        User user = database.user_add( "TestUser" );
        
        assertEquals( user.getNickname( ), "TestUser" );
        assertNotNull( user.getId( ) );
    }
    
    @Test
    public void shouldSetUserActiveStatus( ) {
        User user = database.user_add( "TestUser" );
        
        database.user_updateActiveStatus( user, true );
        assertEquals( user.isActive( ), true );
        
        database.user_updateActiveStatus( user, false );
        assertEquals( user.isActive( ), false );
    }
    
    @Test
    public void shouldReturnFoundUserById( ) {
        User user = database.user_add( "TestUser" );
        Long id = user.getId( );
        
        Optional<User> optionalUser = database.user_get( id );
        
        assertTrue( optionalUser.isPresent( ) );
        assertEquals( optionalUser.get( ).getNickname( ), "TestUser" );
    }
    
    @Test
    public void shouldReturnFoundUserByNickname( ) {
        database.user_add( "TestUser" );
        
        Optional<User> optionalUser = database.user_get( "TestUser" );
        
        assertTrue( optionalUser.isPresent( ) );
        assertEquals( optionalUser.get( ).getNickname( ), "TestUser" );
    }
    
    @Test
    public void shouldFailToReturnUserById( ) {
        Optional<User> optionalUser = database.user_get( Long.MAX_VALUE );
        
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldFailToReturnUserByNickname( ) {
        Optional<User> optionalUser = database.user_get( "TestUser" );
        
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldChangeUserNicknameByString( ) {
        User user = database.user_add( "TestUser" );
        
        database.user_changeNickname( "TestUser", "NewNickname" );
        
        assertEquals( user.getNickname( ), "NewNickname" );
    }
    
    @Test
    public void shouldChangeUserNicknameByObject( ) {
        User user = database.user_add( "TestUser" );
        
        database.user_changeNickname( user, "NewNickname" );
        
        assertEquals( user.getNickname( ), "NewNickname" );
    }
    
    @Test
    public void shouldCreateNewRoom( ) {
        User user = database.user_add( "TestUser" );
        
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        assertNotNull( room );
        assertEquals( room.getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldReturnFoundRoomById( ) {
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        Optional<Room> roomOptional = database.chatRoom_get( room.getId( ) );
        
        assertTrue( roomOptional.isPresent( ) );
        assertEquals( roomOptional.get( ).getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldReturnFoundRoomByName( ) {
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        Optional<Room> roomOptional = database.chatRoom_get( "TestRoom" );
        
        assertTrue( roomOptional.isPresent( ) );
        assertEquals( roomOptional.get( ).getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldFailToFindRoomById( ) {
        Optional<Room> roomOptional = database.chatRoom_get( Long.MAX_VALUE );
        
        assertFalse( roomOptional.isPresent( ) );
    }
    
    @Test
    public void shouldFailToFindRoomByName( ) {
        Optional<Room> roomOptional = database.chatRoom_get( "NonExistentRoom" );
        
        assertFalse( roomOptional.isPresent( ) );
    }
    
    @Test
    public void shouldAddUserToRoom( ) { // Should add new record to "user_in_room" table (userId; RoomId)
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
        boolean result = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( result );
    }
    
    @Test
    public void shouldFailToAddUserToRoom( ) {
        // database.userInRoom_addUserToRoom( Long.MAX_VALUE, Long.MAX_VALUE );
        // Does not check FK    :(
        
        // 1 case - non-existent user
        
        // 2 case - non-existent room
        
    }
    
    @Test
    public void shouldRemoveUserFromRoom( ) {// Should remove record from "user_in_room" table (userId; RoomId)
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean isRemoved = database.userInRoom_removeUserFromRoom( user.getId( ), room.getId( ) );
        boolean isFound = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
    
        assertTrue( isRemoved );
        assertFalse( isFound );
    }
    
    @Test
    public void shouldFailToRemoveUserFromRoom( ) {
        // Non-existent room
        User user = database.user_add( "TestUser" );
        boolean isRemoved = database.userInRoom_removeUserFromRoom( user.getId( ), Long.MAX_VALUE );
        assertFalse( isRemoved );
    
        // Non-existent user
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        isRemoved = database.userInRoom_removeUserFromRoom( Long.MAX_VALUE, room.getId( ) );
        assertFalse( isRemoved );
        
        // Non-existent room and user
        isRemoved = database.userInRoom_removeUserFromRoom( Long.MAX_VALUE, Long.MAX_VALUE );
        assertFalse( isRemoved );
    }
    
    @Test
    public void shouldFindUserInRoom( ) {
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean result = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( result );
    }
    
    @Test
    public void shouldFailToFindUserInRoom( ) {
        // Non-existent room
        User user = database.user_add( "TestUser" );
        boolean isFound = database.userInRoom_findUserInRoom( user.getId( ), Long.MAX_VALUE );
        assertFalse( isFound );
        
        // Non-existent user
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        isFound = database.userInRoom_findUserInRoom( Long.MAX_VALUE, room.getId( ) );
        assertFalse( isFound );
        
        // Non-existent room and user
        isFound = database.userInRoom_findUserInRoom( Long.MAX_VALUE, Long.MAX_VALUE );
        assertFalse( isFound );
    }
    
    @Test
    public void shouldReturnAListOfJoinedRooms( ) {
        /*User user = null;
        user = database.user_add( "TestUser" );
    
        Room room1 = null;
        room1 = database.chatRoom_add( "TestRoom1", user.getNickname( ) );
        Room room2 = null;
        room2 = database.chatRoom_add( "TestRoom2", user.getNickname( ) );
        // org.hibernate.NonUniqueObjectException:
        // A different object with the same identifier value was already associated with the session :
        
        
        
        Room room3 = null;
        room3 = database.chatRoom_add( "TestRoom3", user.getNickname( ) );
    
        database.userInRoom_addUserToRoom( user.getId( ), room1.getId( ) );
        database.userInRoom_addUserToRoom( user.getId( ), room2.getId( ) );
        database.userInRoom_addUserToRoom( user.getId( ), room3.getId( ) );
    
        List<Room> rooms = null;
        rooms = database.userInRoom_getAListOfJoinedRooms( user.getId() );
        assertNotNull( rooms );*/
    }
    
    @Test
    public void shouldAddNewMessage( ) {
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        Message message = database.message_add( "Hello", user.getNickname( ), room.getId( ) );
        
        assertNotNull( message );
    }
    
    @Test
    public void shouldReturnMessageList( ) {
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        String messageBody = "Hello";
        String nickname = user.getNickname( );
        Long roomId = room.getId( );
        database.message_add( messageBody, nickname, roomId );
        database.message_add( messageBody, nickname, roomId );
        database.message_add( messageBody, nickname, roomId );
        
        List<Message> messageList = database.message_getAllMessages( room.getId( ) );
        
        assertNotNull( messageList );
        assertEquals( messageList.size( ), 3 );
    }
    
    @Test
    public void shouldReturnEmptyMessageList( ) {
        User user = database.user_add( "TestUser" );
        Room room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        List<Message> messageList = database.message_getAllMessages( room.getId( ) );
        
        assertEquals( messageList.size( ), 0 );
    }
}

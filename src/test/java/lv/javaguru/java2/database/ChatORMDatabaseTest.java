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
        User user = null;
        
        user = database.user_add( "TestUser" );
        
        assertEquals( user.getNickname( ), "TestUser" );
        assertNotNull( user.getId( ) );
    }
    
    @Test
    public void shouldSetUserActiveStatus( ) {
        // add user for test method
        User user = null;
        user = database.user_add( "TestUser" );
        
        database.user_updateActiveStatus( user, true );
        assertEquals( user.isActive( ), true );
        
        database.user_updateActiveStatus( user, false );
        assertEquals( user.isActive( ), false );
    }
    
    @Test
    public void shouldReturnFoundUser( ) {
        User user = null;
        user = database.user_add( "TestUser" );
        Long id = user.getId( );
        
        Optional<User> optionalUser = null;
        optionalUser = database.user_get( id );
        
        assertTrue( optionalUser.isPresent( ) );
        
        optionalUser = null;
        optionalUser = database.user_get( "TestUser" );
        assertTrue( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldChangeUserNickname( ) {
        User user = null;
        user = database.user_add( "TestUser" );
        
        database.user_changeNickname( "TestUser", "NewNickname1" );
        assertEquals( user.getNickname( ), "NewNickname1" );
        
        database.user_changeNickname( user, "NewNickname2" );
        assertEquals( user.getNickname( ), "NewNickname2" );
    }
    
    @Test
    public void shouldCreateNewRoom( ) {
        User user = null;
        user = database.user_add( "TestUser" );
        
        Room room = null;
        room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        assertNotNull( room );
    }
    
    @Test
    public void shouldReturnFoundRoom( ) {
        User user = null;
        user = database.user_add( "TestUser" );
        assertNotNull( user );
        
        Room room = null;
        room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        assertNotNull( room );
        
        Optional<Room> roomOptional = null;
        Long id = room.getId( );
        roomOptional = database.chatRoom_get( id );
        
        assertTrue( roomOptional.isPresent( ) );
    }
    
    @Test
    public void shouldAddUserToRoom( ) { // Should add new record to "user_in_room" table (userId; RoomId)
        User user = null;
        user = database.user_add( "TestUser" );
        
        Room room = null;
        room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean result = false;
        result = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( result );
    }
    
    @Test
    public void shouldRemoveUserFromRoom( ) {// Should remove record from "user_in_room" table (userId; RoomId)
        User user = null;
        user = database.user_add( "TestUser" );
        
        Room room = null;
        room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
        
        // Check if test user successfully added to test room
        boolean result = false;
        result = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
        assertTrue( result );
        
        
        database.userInRoom_removeUserFromRoom( user.getId( ), room.getId( ) );
        result = true;
        result = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
        
        assertFalse( result );
    }
    
    @Test
    public void shouldFindUserInRoom( ) {
        User user = null;
        user = database.user_add( "TestUser" );
        
        Room room = null;
        room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        
        database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean result = false;
        result = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( result );
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
        User user = null;
        user = database.user_add( "TestUser" );
        assertNotNull( user );
        
        Room room = null;
        room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        assertNotNull( room );
        
        String messageBody = "Hello";
        String nickname = user.getNickname( );
        Long roomId = room.getId( );
        Message message = null;
        message = database.message_add( messageBody, nickname, roomId );
        assertNotNull( message );
    }
    
    @Test
    public void shouldReturnMessageList() {
        User user = null;
        user = database.user_add( "TestUser" );
        assertNotNull( user );
    
        Room room = null;
        room = database.chatRoom_add( "TestRoom", user.getNickname( ) );
        assertNotNull( room );
    
        String messageBody = "Hello";
        String nickname = user.getNickname( );
        Long roomId = room.getId( );
        Message message = null;
        message = database.message_add( messageBody, nickname, roomId );
        message = database.message_add( messageBody, nickname, roomId );
        message = database.message_add( messageBody, nickname, roomId );
        assertNotNull( message );
        
        List<Message> messageList = null;
        messageList = database.message_getAllMessages( room.getId() );
        assertNotNull( messageList );
        assertEquals( messageList.size(), 3 );
    }
}

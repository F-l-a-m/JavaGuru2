package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class UserInRoomRepositoryImplTest {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private UserInRoomRepository userInRoomRepository;
    
    /*@Test
    public void shouldAddUserToRoom( ) { // Should save new record to "user_in_room" table (userId; RoomId)
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        
        userInRoomRepository.addUserToRoom( user.getId( ), room.getId( ) );
        boolean result = userInRoomRepository.findUserInRoom( user.getId( ), room.getId( ) );
        
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
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        userInRoomRepository.addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean isRemoved = userInRoomRepository.removeUserFromRoom( user.getId( ), room.getId( ) );
        boolean isFound = userInRoomRepository.findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( isRemoved );
        assertFalse( isFound );
    }
    
    @Test
    public void shouldFailToRemoveUserFromRoom( ) {
        // Non-existent room
        User user = userRepository.save( "TestUser" );
        boolean isRemoved = userInRoomRepository.removeUserFromRoom( user.getId( ), Long.MAX_VALUE );
        assertFalse( isRemoved );
        
        // Non-existent user
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        isRemoved = userInRoomRepository.removeUserFromRoom( Long.MAX_VALUE, room.getId( ) );
        assertFalse( isRemoved );
        
        // Non-existent room and user
        isRemoved = userInRoomRepository.removeUserFromRoom( Long.MAX_VALUE, Long.MAX_VALUE );
        assertFalse( isRemoved );
    }
    
    @Test
    public void shouldFindUserInRoom( ) {
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        userInRoomRepository.addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean result = userInRoomRepository.findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( result );
    }
    
    @Test
    public void shouldFailToFindUserInRoom( ) {
        // Non-existent room
        User user = userRepository.save( "TestUser" );
        boolean isFound = userInRoomRepository.findUserInRoom( user.getId( ), Long.MAX_VALUE );
        assertFalse( isFound );
        
        // Non-existent user
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        isFound = userInRoomRepository.findUserInRoom( Long.MAX_VALUE, room.getId( ) );
        assertFalse( isFound );
        
        // Non-existent room and user
        isFound = userInRoomRepository.findUserInRoom( Long.MAX_VALUE, Long.MAX_VALUE );
        assertFalse( isFound );
    }*/
    
    @Test
    public void shouldReturnAListOfJoinedRooms( ) {
        /*User user = null;
        user = userRepository.save( "TestUser" );
    
        Room room1 = null;
        room1 = roomRepository.save( "TestRoom1", user.getNickname( ) );
        Room room2 = null;
        room2 = roomRepository.save( "TestRoom2", user.getNickname( ) );
        // org.hibernate.NonUniqueObjectException:
        // A different object with the same identifier value was already associated with the session :
        
        
        
        Room room3 = null;
        room3 = roomRepository.save( "TestRoom3", user.getNickname( ) );
    
        userInRoomRepository.addUserToRoom( user.getId( ), room1.getId( ) );
        userInRoomRepository.addUserToRoom( user.getId( ), room2.getId( ) );
        userInRoomRepository.addUserToRoom( user.getId( ), room3.getId( ) );
    
        List<Room> rooms = null;
        rooms = userInRoomRepository.getAListOfJoinedRooms( user.getId() );
        assertNotNull( rooms );*/
    }
}

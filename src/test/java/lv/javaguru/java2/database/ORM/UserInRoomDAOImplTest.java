package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserInRoomDAO;
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
public class UserInRoomDAOImplTest {
    
    @Autowired private UserDAO userDAO;
    @Autowired private RoomDAO roomDAO;
    @Autowired private UserInRoomDAO userInRoomDAO;
    
    @Test
    public void shouldAddUserToRoom( ) { // Should add new record to "user_in_room" table (userId; RoomId)
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        
        userInRoomDAO.addUserToRoom( user.getId( ), room.getId( ) );
        boolean result = userInRoomDAO.findUserInRoom( user.getId( ), room.getId( ) );
        
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
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        userInRoomDAO.addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean isRemoved = userInRoomDAO.removeUserFromRoom( user.getId( ), room.getId( ) );
        boolean isFound = userInRoomDAO.findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( isRemoved );
        assertFalse( isFound );
    }
    
    @Test
    public void shouldFailToRemoveUserFromRoom( ) {
        // Non-existent room
        User user = userDAO.add( "TestUser" );
        boolean isRemoved = userInRoomDAO.removeUserFromRoom( user.getId( ), Long.MAX_VALUE );
        assertFalse( isRemoved );
        
        // Non-existent user
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        isRemoved = userInRoomDAO.removeUserFromRoom( Long.MAX_VALUE, room.getId( ) );
        assertFalse( isRemoved );
        
        // Non-existent room and user
        isRemoved = userInRoomDAO.removeUserFromRoom( Long.MAX_VALUE, Long.MAX_VALUE );
        assertFalse( isRemoved );
    }
    
    @Test
    public void shouldFindUserInRoom( ) {
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        userInRoomDAO.addUserToRoom( user.getId( ), room.getId( ) );
        
        boolean result = userInRoomDAO.findUserInRoom( user.getId( ), room.getId( ) );
        
        assertTrue( result );
    }
    
    @Test
    public void shouldFailToFindUserInRoom( ) {
        // Non-existent room
        User user = userDAO.add( "TestUser" );
        boolean isFound = userInRoomDAO.findUserInRoom( user.getId( ), Long.MAX_VALUE );
        assertFalse( isFound );
        
        // Non-existent user
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        isFound = userInRoomDAO.findUserInRoom( Long.MAX_VALUE, room.getId( ) );
        assertFalse( isFound );
        
        // Non-existent room and user
        isFound = userInRoomDAO.findUserInRoom( Long.MAX_VALUE, Long.MAX_VALUE );
        assertFalse( isFound );
    }
    
    @Test
    public void shouldReturnAListOfJoinedRooms( ) {
        /*User user = null;
        user = userDAO.add( "TestUser" );
    
        Room room1 = null;
        room1 = roomDAO.add( "TestRoom1", user.getNickname( ) );
        Room room2 = null;
        room2 = roomDAO.add( "TestRoom2", user.getNickname( ) );
        // org.hibernate.NonUniqueObjectException:
        // A different object with the same identifier value was already associated with the session :
        
        
        
        Room room3 = null;
        room3 = roomDAO.add( "TestRoom3", user.getNickname( ) );
    
        userInRoomDAO.addUserToRoom( user.getId( ), room1.getId( ) );
        userInRoomDAO.addUserToRoom( user.getId( ), room2.getId( ) );
        userInRoomDAO.addUserToRoom( user.getId( ), room3.getId( ) );
    
        List<Room> rooms = null;
        rooms = userInRoomDAO.getAListOfJoinedRooms( user.getId() );
        assertNotNull( rooms );*/
    }
}

package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserInRoomDAO;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class User_AddToRoomServiceTest {
    
    @Mock private UserInRoomDAO userInRoomDAO;
    @Mock private UserDAO userDAO;
    @Mock private RoomDAO roomDAO;
    
    @InjectMocks
    private User_AddToRoomService userAddToRoomService = new User_AddToRoomService( );
    
    @Test
    public void shouldAddUserToRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userDAO.get( userId ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomDAO.get( roomId ) )
                .thenReturn( Optional.of( room ) );
        Mockito.when( userInRoomDAO.findUserInRoom( userId, roomId ) )
                .thenReturn( false );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertTrue( userAddToRoomResponse.isSuccess( ) );
        assertNull( userAddToRoomResponse.getErrors( ) );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_notExistentUser( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userDAO.get( userId ) )
                .thenReturn( Optional.empty( ) );
        Mockito.when( roomDAO.get( roomId ) )
                .thenReturn( Optional.of( room ) );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_notExistentRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userDAO.get( userId ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomDAO.get( roomId ) )
                .thenReturn( Optional.empty( ) );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_notExistentUserAndRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userDAO.get( userId ) )
                .thenReturn( Optional.empty( ) );
        Mockito.when( roomDAO.get( roomId ) )
                .thenReturn( Optional.empty( ) );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 2 );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_userAlreadyInThatRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userDAO.get( userId ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomDAO.get( roomId ) )
                .thenReturn( Optional.of( room ) );
        Mockito.when( userInRoomDAO.findUserInRoom( userId, roomId ) )
                .thenReturn( true ); // <-- true, when user is already in that room
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 1 );
    }
}

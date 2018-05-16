package lv.javaguru.java2.console.businesslogic.userInRoom;

import lv.javaguru.java2.console.database.RoomRepository;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.database.UserInRoomRepository;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
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
    
    @Mock private UserInRoomRepository userInRoomRepository;
    @Mock private UserRepository userRepository;
    @Mock private RoomRepository roomRepository;
    
    @InjectMocks
    private User_AddToRoomService userAddToRoomService = new User_AddToRoomService( );
    
    @Test
    public void shouldAddUserToRoom( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.of( room ) );
        Mockito.when( userInRoomRepository.findUserInRoom( user, room ) )
                .thenReturn( false );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertTrue( userAddToRoomResponse.isSuccess( ) );
        assertNull( userAddToRoomResponse.getErrors( ) );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_notExistentUser( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.empty( ) );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.of( room ) );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_notExistentRoom( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.empty( ) );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_notExistentUserAndRoom( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.empty( ) );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.empty( ) );
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 2 );
    }
    
    @Test
    public void shouldFailToAddUserToRoom_userAlreadyInThatRoom( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.of( room ) );
        Mockito.when( userInRoomRepository.findUserInRoom( user, room ) )
                .thenReturn( true ); // <-- true, when user is already in that room
        
        User_AddToRoomResponse userAddToRoomResponse = userAddToRoomService.add( user, room );
        
        assertFalse( userAddToRoomResponse.isSuccess( ) );
        assertNotNull( userAddToRoomResponse.getErrors( ) );
        assertEquals( userAddToRoomResponse.getErrors( ).size( ), 1 );
    }
}

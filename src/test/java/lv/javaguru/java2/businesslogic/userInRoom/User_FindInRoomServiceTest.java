package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.database.UserInRoomRepository;
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
public class User_FindInRoomServiceTest {
    
    @Mock private UserInRoomRepository userInRoomRepository;
    @Mock private UserRepository userRepository;
    @Mock private RoomRepository roomRepository;
    
    @InjectMocks
    private User_FindInRoomService userFindInRoomService = new User_FindInRoomService( );
    
    @Test
    public void shouldFindUserInRoom( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.of( room ) );
        Mockito.when( userInRoomRepository.findUserInRoom( user, room ) )
                .thenReturn( true );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertTrue( userFindInRoomResponse.isSuccess( ) );
        assertNull( userFindInRoomResponse.getErrors( ) );
    }
    
    @Test
    public void shouldNotFindUserInRoom_notExistentUser( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.empty() );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.of( room ) );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldNotFindUserInRoom_notExistentRoom( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.empty() );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldNotFindUserInRoom_notExistentUserAndRoom( ) {
        User user = new User( );
        user.setNickname( "Nickname" );
        Room room = new Room( );
        room.setName( "RoomName" );
        Mockito.when( userRepository.getByNickname( "Nickname" ) )
                .thenReturn( Optional.empty() );
        Mockito.when( roomRepository.get( "RoomName" ) )
                .thenReturn( Optional.empty() );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 2 );
    }
    
    @Test
    public void shouldNotFindUserInRoom_userIsNotInThatRoom( ) {
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
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 1 );
    }
}

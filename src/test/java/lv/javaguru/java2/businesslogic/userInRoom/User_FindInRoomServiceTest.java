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
    
    /*@Test
    public void shouldFindUserInRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userRepository.get( userId ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( roomId ) )
                .thenReturn( Optional.of( room ) );
        Mockito.when( userInRoomRepository.findUserInRoom( userId, roomId ) )
                .thenReturn( true );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertTrue( userFindInRoomResponse.isSuccess( ) );
        assertNull( userFindInRoomResponse.getErrors( ) );
    }
    
    @Test
    public void shouldNotFindUserInRoom_notExistentUser( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userRepository.get( userId ) )
                .thenReturn( Optional.empty( ) );
        Mockito.when( roomRepository.get( roomId ) )
                .thenReturn( Optional.of( room ) );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldNotFindUserInRoom_notExistentRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userRepository.get( userId ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( roomId ) )
                .thenReturn( Optional.empty( ) );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 1 );
    }
    
    @Test
    public void shouldNotFindUserInRoom_notExistentUserAndRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userRepository.get( userId ) )
                .thenReturn( Optional.empty( ) );
        Mockito.when( roomRepository.get( roomId ) )
                .thenReturn( Optional.empty( ) );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 2 );
    }
    
    @Test
    public void shouldNotFindUserInRoom_userIsNotInThatRoom( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        Long roomId = Integer.toUnsignedLong( 1 );
        User user = new User( );
        user.setId( userId );
        Room room = new Room( );
        room.setId( roomId );
        Mockito.when( userRepository.get( userId ) )
                .thenReturn( Optional.of( user ) );
        Mockito.when( roomRepository.get( roomId ) )
                .thenReturn( Optional.of( room ) );
        Mockito.when( userInRoomRepository.findUserInRoom( userId, roomId ) )
                .thenReturn( false );
        
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        
        assertFalse( userFindInRoomResponse.isSuccess( ) );
        assertNotNull( userFindInRoomResponse.getErrors( ) );
        assertEquals( userFindInRoomResponse.getErrors( ).size( ), 1 );
    }*/
}

package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.room.Room_NameValidator;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class Room_JoinOrCreateServiceTest {
    
    @Mock private Room_NameValidator validator;
    @Mock private RoomRepository roomRepository;
    @Mock private UserInRoomRepository userInRoomRepository;
    
    @InjectMocks
    private Room_JoinOrCreateService joinOrCreateService = new Room_JoinOrCreateService( );
    
    @Test
    public void shouldCreateChatRoomAndJoinUser( ) {
        String roomName = "TestRoom";
        User user = Mockito.mock( User.class );
        //user.setNickname( "TestUser" );
        //user.setId( Integer.toUnsignedLong( 1 ) );
        List<Error> errors = new ArrayList<>( );
        Mockito.when( validator.validate( roomName ) )
                .thenReturn( errors );
        Mockito.when( roomRepository.get( roomName ) )
                .thenReturn( Optional.empty( ) ); // Room does not exists, need to create new
        
        Room_JoinOrCreateResponse response = joinOrCreateService.joinOrCreateRoom( roomName, user );
        
        assertTrue( response.isSuccess( ) );
        assertNull( response.getErrors( ) );
        assertNotNull( response.getRoom( ) );
    }
    
    @Test
    public void shouldJoinExistingChatRoom( ) {
        String roomName = "TestRoom";
        User user = Mockito.mock( User.class );
        user.setNickname( "TestUser" );
        user.setId( Integer.toUnsignedLong( 1 ) );
        List<Error> errors = new ArrayList<>( );
        Mockito.when( validator.validate( roomName ) )
                .thenReturn( errors );
        Room room = Mockito.mock( Room.class );
        room.setId( Integer.toUnsignedLong( 1 ) );
        Mockito.when( roomRepository.get( roomName ) )
                .thenReturn( Optional.of( room ) ); // Room is already in db
        Mockito.when( userInRoomRepository.findUserInRoom( user, room ) )
                .thenReturn( false ); // User is not in room
        
        Room_JoinOrCreateResponse response = joinOrCreateService.joinOrCreateRoom( roomName, user );
        
        assertTrue( response.isSuccess( ) );
        assertNull( response.getErrors( ) );
        assertNotNull( response.getRoom( ) );
    }
    
    @Test
    public void shouldSuccessBecauseUserIsAlreadyInThatRoom( ) {
        String roomName = "TestRoom";
        User user = Mockito.mock( User.class );
        user.setNickname( "TestUser" );
        user.setId( Integer.toUnsignedLong( 1 ) );
        List<Error> errors = new ArrayList<>( );
        Mockito.when( validator.validate( roomName ) )
                .thenReturn( errors );
        Room room = Mockito.mock( Room.class );
        room.setId( Integer.toUnsignedLong( 1 ) );
        Mockito.when( roomRepository.get( roomName ) )
                .thenReturn( Optional.of( room ) ); // Room is already in db
        Mockito.when( userInRoomRepository.findUserInRoom( user, room ) )
                .thenReturn( true ); // User is already in that room
        
        Room_JoinOrCreateResponse response = joinOrCreateService.joinOrCreateRoom( roomName, user );
        
        assertTrue( response.isSuccess( ) );
        assertNull( response.getErrors( ) );
        assertNotNull( response.getRoom( ) );
    }
}

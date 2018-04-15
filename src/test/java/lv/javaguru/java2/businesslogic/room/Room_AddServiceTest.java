package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.User_NicknameValidator;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.domain.Room;
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
public class Room_AddServiceTest {
    
    @Mock RoomRepository roomRepository;
    @Mock User_NicknameValidator nicknameValidator;
    @Mock Room_NameValidator roomNameValidator;
    
    @InjectMocks
    Room_AddService addService = new Room_AddService( );
    
    @Test
    public void shouldAddRoom( ) {
        String roomName = "TestRoom";
        String creatorNickname = "TestNickname";
        Room room = Mockito.mock( Room.class );
        List<Error> errors = new ArrayList<>( );
        Mockito.when( roomNameValidator.validate( roomName ) )
                .thenReturn( errors );
        Mockito.when( nicknameValidator.validate( creatorNickname ) )
                .thenReturn( errors );
        Mockito.when( roomRepository.get( roomName ) )
                .thenReturn( Optional.empty( ) ); // Room not found, create new
        
        Room_AddResponse response = addService.addRoom( roomName, creatorNickname );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getRoom( ) );
        assertNull( response.getErrors( ) );
    }
    
    @Test
    public void shouldFindExistingRoom( ) {
        String roomName = "TestRoom";
        String creatorNickname = "TestNickname";
        Room room = Mockito.mock( Room.class );
        List<Error> errors = new ArrayList<>( );
        Mockito.when( roomNameValidator.validate( roomName ) )
                .thenReturn( errors );
        Mockito.when( nicknameValidator.validate( creatorNickname ) )
                .thenReturn( errors );
        Mockito.when( roomRepository.get( roomName ) )
                .thenReturn( Optional.of( room ) ); // Room already exists in db
        
        Room_AddResponse response = addService.addRoom( roomName, creatorNickname );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getRoom( ) );
        assertNull( response.getErrors( ) );
    }
}

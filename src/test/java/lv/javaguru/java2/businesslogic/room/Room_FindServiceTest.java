package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
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
public class Room_FindServiceTest {
    
    @Mock Database database;
    @Mock Room_NameValidator validator;
    
    @InjectMocks
    Room_FindService roomFindService = new Room_FindService( );
    
    @Test
    public void shouldFindRoomByName( ) {
        String roomName = "TestRoom";
        Room room = Mockito.mock( Room.class );
        List<Error> errors = new ArrayList<>( );
        Mockito.when( validator.validate( roomName ) )
                .thenReturn( errors );
        Mockito.when( database.chatRoom_get( roomName ) )
                .thenReturn( Optional.of( room ) );
        
        Room_FindResponse response = roomFindService.find( roomName );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getRoom( ) );
        assertNull( response.getErrors( ) );
    }
    
    @Test
    public void shouldFindRoomById( ) {
        Long roomId = Integer.toUnsignedLong( 1 );
        Room room = Mockito.mock( Room.class );
        Mockito.when( database.chatRoom_get( roomId ) )
                .thenReturn( Optional.of( room ) );
        
        Room_FindResponse response = roomFindService.find( roomId );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getRoom( ) );
        assertNull( response.getErrors( ) );
    }
    
    @Test
    public void shouldNotFindRoomByName( ) {
        String roomName = "TestRoom";
        List<Error> errors = new ArrayList<>( );
        Mockito.when( validator.validate( roomName ) )
                .thenReturn( errors );
        Mockito.when( database.chatRoom_get( roomName ) )
                .thenReturn( Optional.empty( ) );
        
        Room_FindResponse response = roomFindService.find( roomName );
        
        assertFalse( response.isSuccess( ) );
        assertNull( response.getRoom( ) );
        assertNotNull( response.getErrors( ) );
    }
    
    @Test
    public void shouldNotFindRoomById( ) {
        Long roomId = Integer.toUnsignedLong( 1 );
        Mockito.when( database.chatRoom_get( roomId ) )
                .thenReturn( Optional.empty( ) );
        
        Room_FindResponse response = roomFindService.find( roomId );
        
        assertFalse( response.isSuccess( ) );
        assertNull( response.getRoom( ) );
        assertNotNull( response.getErrors( ) );
    }
}

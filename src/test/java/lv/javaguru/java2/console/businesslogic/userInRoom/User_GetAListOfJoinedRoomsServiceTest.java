package lv.javaguru.java2.console.businesslogic.userInRoom;

import lv.javaguru.java2.console.database.UserInRoomRepository;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class User_GetAListOfJoinedRoomsServiceTest {
    
    @Mock UserInRoomRepository userInRoomRepository;
    
    @InjectMocks
    User_GetAListOfJoinedRoomsService userGetAListOfJoinedRoomsService = new User_GetAListOfJoinedRoomsService( );
    
    @Test
    public void shouldReturnAListOfRooms( ) {
        User user = Mockito.mock( User.class );
        List<Room> roomList = new ArrayList<>( );
        Room room = Mockito.mock( Room.class );
        roomList.add( room );
        roomList.add( room );
        roomList.add( room );
        Mockito.when( userInRoomRepository.getAListOfJoinedRooms( user ) )
                .thenReturn( roomList );
        
        List<Room> roomsFromService = userGetAListOfJoinedRoomsService.getList( user );
        
        assertNotNull( roomsFromService );
        assertEquals( roomsFromService.size( ), roomList.size( ) );
    }
    
    @Test
    public void shouldReturnAListOfStrings( ) {
        User user = Mockito.mock( User.class );
        List<Room> roomList = new ArrayList<>( );
        Room room = Mockito.mock( Room.class );
        roomList.add( room );
        roomList.add( room );
        roomList.add( room );
        Mockito.when( userInRoomRepository.getAListOfJoinedRooms( user ) )
                .thenReturn( roomList );
    
        List<String> stringListFromService = userGetAListOfJoinedRoomsService.getAStringListOfJoinedRooms( user );
    
        assertNotNull( stringListFromService );
        assertEquals( stringListFromService.size( ), roomList.size( ) );
    }
}

package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.database.UserInRoomDAO;
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

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class User_GetAListOfJoinedRoomsServiceTest {
    
    @Mock UserInRoomDAO userInRoomDAO;
    
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
        Mockito.when( userInRoomDAO.getAListOfJoinedRooms( user.getId( ) ) )
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
        Mockito.when( userInRoomDAO.getAListOfJoinedRooms( user.getId( ) ) )
                .thenReturn( roomList );
    
        List<String> stringListFromService = userGetAListOfJoinedRoomsService.getAStringListOfJoinedRooms( user );
    
        assertNotNull( stringListFromService );
        assertEquals( stringListFromService.size( ), roomList.size( ) );
    }
}

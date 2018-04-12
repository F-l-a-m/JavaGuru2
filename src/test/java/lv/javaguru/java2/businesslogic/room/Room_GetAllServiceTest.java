package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.domain.Room;
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
public class Room_GetAllServiceTest {
    
    @Mock RoomDAO roomDAO;
    
    @InjectMocks
    Room_GetAllService roomGetAllService = new Room_GetAllService( );
    
    @Test
    public void shouldReturnRoomList( ) {
        List<Room> roomList = new ArrayList<>( );
        Room room = Mockito.mock( Room.class );
        roomList.add( room );
        roomList.add( room );
        roomList.add( room );
        Mockito.when( roomDAO.getAllRooms( ) )
                .thenReturn( roomList );
        
        Room_GetAllResponse response = roomGetAllService.getRoomList( );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getListOfAllRooms( ) );
    }
    
    @Test
    public void shouldReturnStringList( ) {
        List<Room> roomList = new ArrayList<>( );
        Room room = Mockito.mock( Room.class );
        roomList.add( room );
        roomList.add( room );
        roomList.add( room );
        Mockito.when( roomDAO.getAllRooms( ) )
                .thenReturn( roomList );
        
        Room_GetAllResponse response = roomGetAllService.getStringList( );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getListOfAllRooms( ) );
    }
}

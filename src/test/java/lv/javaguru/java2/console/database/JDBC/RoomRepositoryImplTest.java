package lv.javaguru.java2.console.database.JDBC;

import lv.javaguru.java2.console.domain.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static lv.javaguru.java2.console.domain.builders.RoomBuilder.createRoom;
import static org.junit.Assert.*;

public class RoomRepositoryImplTest {
    
    private RoomRepositoryImpl repository = new RoomRepositoryImpl( );
    private Room room;
    
    @Before
    public void init( ) {
        room = createRoom( )
                .withName( "TestRoom" )
                .withCreatorNickname( "TestUser" )
                .build( );
        repository.save( room );
        System.out.println( "Room " + room.getName( ) + " successfully added to database." );
    }
    
    @Test
    public void shouldAddRoom( ) {
        System.out.println( "shouldAddRoom" );
        assertNotNull( room.getId( ) );
        System.out.println( "Room id is: " + room.getId( ) );
    }
    
    @Test
    public void shouldFindRoom( ) {
        System.out.println( "shouldFindRoom" );
        Optional<Room> optionalRoom = repository.get( "TestRoom" );
        if ( optionalRoom.isPresent( ) ) {
            System.out.println( "Room " + room.getName( ) + " has been found in database." );
        }
        assertTrue( optionalRoom.isPresent( ) );
    }
    
    @Test
    public void shouldNotFindRoom( ) {
        System.out.println( "shouldNotFindRoom" );
        Optional<Room> optionalRoom = repository.get( "TestRoom2" );
        if ( !optionalRoom.isPresent( ) ) {
            System.out.println( "Room TestRoom2 has not been found in database." );
        }
        assertFalse( optionalRoom.isPresent( ) );
    }
    
    @After
    public void cleanDatabase( ) {
        repository.deleteRoom( room );
        System.out.println( "Room " + room.getName( ) + " successfully deleted from database.\n\n" );
    }
}

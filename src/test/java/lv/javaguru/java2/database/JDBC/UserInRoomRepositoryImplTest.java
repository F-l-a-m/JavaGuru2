package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static lv.javaguru.java2.domain.builders.RoomBuilder.createRoom;
import static lv.javaguru.java2.domain.builders.UserBuilder.createUser;
import static org.junit.Assert.*;

public class UserInRoomRepositoryImplTest {
    
    private User user;
    private Room room;
    private UserRepositoryImpl userRepository = new UserRepositoryImpl( );
    private RoomRepositoryImpl roomRepository = new RoomRepositoryImpl( );
    private UserInRoomRepositoryImpl userInRoomRepository = new UserInRoomRepositoryImpl( );
    
    @Before
    public void init( ) {
        user = createUser( )
                .withNickname( "TestUser" ).build( );
        userRepository.save( user );
        System.out.println( "User " + user.getNickname( ) + " successfully added to database." );
        
        room = createRoom( )
                .withName( "TestRoom" )
                .withCreatorNickname( "TestUser" )
                .build( );
        roomRepository.save( room );
        System.out.println( "Room " + room.getName( ) + " successfully added to database.\n" );
    }
    
    @Test
    public void shouldAddAndFindUserInRoom( ) {
        System.out.println( "shouldAddUserToRoom" );
        userInRoomRepository.addUserToRoom( user, room );
        System.out.println( "Successfully added user " + user.getNickname( ) + " to room " + room.getName( ) );
        boolean isUserInToom = userInRoomRepository.findUserInRoom( user, room );
        System.out.println( "User " + user.getNickname( ) + " has been found in room " + room.getName( ) );
        System.out.println( );
        assertTrue( isUserInToom );
    }
    
    @After
    public void cleanDatabase( ) {
        userRepository.deleteUser( user );
        System.out.println( "User " + user.getNickname( ) + " successfully deleted from database." );
        roomRepository.deleteRoom( room );
        System.out.println( "Room " + room.getName( ) + " successfully deleted from database.\n\n" );
    }
    
}

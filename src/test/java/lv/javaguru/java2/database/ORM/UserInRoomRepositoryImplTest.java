package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserInRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.domain.builders.RoomBuilder.createRoom;
import static lv.javaguru.java2.domain.builders.UserBuilder.createUser;
import static lv.javaguru.java2.domain.builders.UserInRoomBuilder.createUserInRoom;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class UserInRoomRepositoryImplTest {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private UserInRoomRepository userInRoomRepository;
    
    @Test
    public void shouldAddUserToRoom( ) {
        User user = createUser( )
                .withNickname( "TestUser" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        userRepository.save( user );
        
        Room room = createRoom( )
                .withName( "TestRoom" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withCreatorNickname( user.getNickname( ) )
                .build( );
        roomRepository.save( room );
        
        userInRoomRepository.addUserToRoom( user, room );
        
        boolean result = userInRoomRepository.findUserInRoom( user, room );
        
        assertTrue( result );
    }
    
    @Test
    public void shouldRemoveUserFromRoom( ) {
        User user = createUser( )
                .withNickname( "TestUser" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        userRepository.save( user );
        
        Room room = createRoom( )
                .withName( "TestRoom" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withCreatorNickname( user.getNickname( ) )
                .build( );
        roomRepository.save( room );
        
        userInRoomRepository.addUserToRoom( user, room );
        
        userInRoomRepository.removeUserFromRoom( user, room );
        
        boolean isFound = userInRoomRepository.findUserInRoom( user, room );
        
        assertFalse( isFound );
    }
    
    @Test
    public void shouldFailToFindUserInRoom( ) {
        User user = createUser( )
                .withNickname( "TestUser" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        userRepository.save( user );
        
        Room room = createRoom( )
                .withName( "TestRoom" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withCreatorNickname( user.getNickname( ) )
                .build( );
        roomRepository.save( room );
        
        userInRoomRepository.addUserToRoom( user, room );
        
        userInRoomRepository.removeUserFromRoom( user, room );
        
        boolean result = userInRoomRepository.findUserInRoom( user, room );
        
        assertFalse( result );
    }
    
    @Test
    public void shouldReturnAListOfJoinedRooms( ) {
        User user = createUser( )
                .withNickname( "TestUser" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        userRepository.save( user );
        
        Room room1 = createRoom( )
                .withName( "TestRoomOne" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withCreatorNickname( user.getNickname( ) )
                .build( );
        roomRepository.save( room1 );
        
        Room room2 = createRoom( )
                .withName( "TestRoomTwo" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withCreatorNickname( user.getNickname( ) )
                .build( );
        roomRepository.save( room2 );
        
        Room room3 = createRoom( )
                .withName( "TestRoomThree" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withCreatorNickname( user.getNickname( ) )
                .build( );
        roomRepository.save( room3 );
        
        userInRoomRepository.addUserToRoom( user, room1 );
        userInRoomRepository.addUserToRoom( user, room2 );
        userInRoomRepository.addUserToRoom( user, room3 );
        
        List<Room> roomList = userInRoomRepository.getAListOfJoinedRooms( user );
        
        assertFalse( roomList.isEmpty( ) );
        assertEquals( roomList.size( ), 3 );
    }
}

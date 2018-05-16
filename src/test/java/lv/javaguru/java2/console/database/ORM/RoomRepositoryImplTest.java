package lv.javaguru.java2.console.database.ORM;

import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.configs.SpringAppConfig;
import lv.javaguru.java2.console.database.RoomRepository;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static lv.javaguru.java2.console.domain.builders.RoomBuilder.createRoom;
import static lv.javaguru.java2.console.domain.builders.UserBuilder.createUser;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class RoomRepositoryImplTest {
    
    @Autowired private RoomRepository roomRepository;
    @Autowired private UserRepository userRepository;
    
    @Test
    public void shouldCreateNewRoom( ) {
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
        
        assertNotNull( room.getId( ) );
    }
    
    @Test
    public void shouldFailToFindRoom( ) {
        Optional<Room> search = roomRepository.get( "TestRoom" );
        
        assertFalse( search.isPresent( ) );
    }
}

package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static lv.javaguru.java2.domain.builders.RoomBuilder.createRoom;
import static lv.javaguru.java2.domain.builders.UserBuilder.createUser;
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

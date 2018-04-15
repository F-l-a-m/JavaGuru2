package lv.javaguru.java2.database.ORM;

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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class RoomRepositoryImplTest {
    
    @Autowired private RoomRepository roomRepository;
    @Autowired private UserRepository userRepository;
    
    /*@Test
    public void shouldCreateNewRoom( ) {
        User user = userRepository.save( "TestUser" );
        
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        
        assertNotNull( room );
        assertEquals( room.getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldReturnFoundRoomById( ) {
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        
        Optional<Room> roomOptional = roomRepository.get( room.getId( ) );
        
        assertTrue( roomOptional.isPresent( ) );
        assertEquals( roomOptional.get( ).getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldReturnFoundRoomByName( ) {
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        
        Optional<Room> roomOptional = roomRepository.get( "TestRoom" );
        
        assertTrue( roomOptional.isPresent( ) );
        assertEquals( roomOptional.get( ).getName( ), "TestRoom" );
    }*/
    
    @Test
    public void shouldFailToFindRoomById( ) {
        Optional<Room> roomOptional = roomRepository.get( Long.MAX_VALUE );
        
        assertFalse( roomOptional.isPresent( ) );
    }
    
    @Test
    public void shouldFailToFindRoomByName( ) {
        Optional<Room> roomOptional = roomRepository.get( "NonExistentRoom" );
        
        assertFalse( roomOptional.isPresent( ) );
    }
}

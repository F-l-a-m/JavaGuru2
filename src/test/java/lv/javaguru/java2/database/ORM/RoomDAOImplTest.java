package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.database.UserDAO;
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
public class RoomDAOImplTest {
    
    @Autowired private RoomDAO roomDAO;
    @Autowired private UserDAO userDAO;
    
    @Test
    public void shouldCreateNewRoom( ) {
        User user = userDAO.add( "TestUser" );
        
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        
        assertNotNull( room );
        assertEquals( room.getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldReturnFoundRoomById( ) {
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        
        Optional<Room> roomOptional = roomDAO.get( room.getId( ) );
        
        assertTrue( roomOptional.isPresent( ) );
        assertEquals( roomOptional.get( ).getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldReturnFoundRoomByName( ) {
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        
        Optional<Room> roomOptional = roomDAO.get( "TestRoom" );
        
        assertTrue( roomOptional.isPresent( ) );
        assertEquals( roomOptional.get( ).getName( ), "TestRoom" );
    }
    
    @Test
    public void shouldFailToFindRoomById( ) {
        Optional<Room> roomOptional = roomDAO.get( Long.MAX_VALUE );
        
        assertFalse( roomOptional.isPresent( ) );
    }
    
    @Test
    public void shouldFailToFindRoomByName( ) {
        Optional<Room> roomOptional = roomDAO.get( "NonExistentRoom" );
        
        assertFalse( roomOptional.isPresent( ) );
    }
}

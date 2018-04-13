package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.MessageDAO;
import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class MessageDAOImplTest {
    
    @Autowired private UserDAO userDAO;
    @Autowired private RoomDAO roomDAO;
    @Autowired private MessageDAO messageDAO;
    
    @Test
    public void shouldAddNewMessage( ) {
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        
        Message message = messageDAO.add( "Hello", user.getNickname( ), room.getId( ) );
        
        assertNotNull( message );
    }
    
    @Test
    public void shouldReturnMessageList( ) {
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        String messageBody = "Hello";
        String nickname = user.getNickname( );
        Long roomId = room.getId( );
        messageDAO.add( messageBody, nickname, roomId );
        messageDAO.add( messageBody, nickname, roomId );
        messageDAO.add( messageBody, nickname, roomId );
        
        List<Message> messageList = messageDAO.getAllMessages( room.getId( ) );
        
        assertNotNull( messageList );
        assertEquals( messageList.size( ), 3 );
    }
    
    @Test
    public void shouldReturnEmptyMessageList( ) {
        User user = userDAO.add( "TestUser" );
        Room room = roomDAO.add( "TestRoom", user.getNickname( ) );
        
        List<Message> messageList = messageDAO.getAllMessages( room.getId( ) );
        
        assertEquals( messageList.size( ), 0 );
    }
    
}
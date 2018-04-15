package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.MessageRepository;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserRepository;
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
public class MessageRepositoryImplTest {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private MessageRepository messageRepository;
    
    /*@Test
    public void shouldAddNewMessage( ) {
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        
        Message message = messageRepository.save( "Hello", user.getNickname( ), room.getId( ) );
        
        assertNotNull( message );
    }
    
    @Test
    public void shouldReturnMessageList( ) {
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        String messageBody = "Hello";
        String nickname = user.getNickname( );
        Long roomId = room.getId( );
        messageRepository.save( messageBody, nickname, roomId );
        messageRepository.save( messageBody, nickname, roomId );
        messageRepository.save( messageBody, nickname, roomId );
        
        List<Message> messageList = messageRepository.getAllMessages( room.getId( ) );
        
        assertNotNull( messageList );
        assertEquals( messageList.size( ), 3 );
    }
    
    @Test
    public void shouldReturnEmptyMessageList( ) {
        User user = userRepository.save( "TestUser" );
        Room room = roomRepository.save( "TestRoom", user.getNickname( ) );
        
        List<Message> messageList = messageRepository.getAllMessages( room.getId( ) );
        
        assertEquals( messageList.size( ), 0 );
    }*/
    
}
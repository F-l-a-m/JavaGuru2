package lv.javaguru.java2.console.database.ORM;

import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.configs.SpringAppConfig;
import lv.javaguru.java2.console.database.MessageRepository;
import lv.javaguru.java2.console.database.RoomRepository;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.Message;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.console.domain.builders.MessageBuilder.createMessage;
import static lv.javaguru.java2.console.domain.builders.RoomBuilder.createRoom;
import static lv.javaguru.java2.console.domain.builders.UserBuilder.createUser;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class MessageRepositoryImplTest {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private MessageRepository messageRepository;
    
    @Test
    public void shouldAddNewMessage( ) {
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
        
        Message message = createMessage( )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withUserNickname( user.getNickname( ) )
                .withMessageBody( "Hello World!" )
                .withRoom( room ).build( );
        messageRepository.save( message );
        
        assertNotNull( message.getId( ) );
    }
    
    @Test
    public void shouldReturnMessageList( ) {
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
    
    
        Message message1 = createMessage( )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withUserNickname( user.getNickname( ) )
                .withMessageBody( "Message One" )
                .withRoom( room ).build( );
        Message message2 = createMessage( )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withUserNickname( user.getNickname( ) )
                .withMessageBody( "Message Two" )
                .withRoom( room ).build( );
        Message message3 = createMessage( )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withUserNickname( user.getNickname( ) )
                .withMessageBody( "Message Three" )
                .withRoom( room ).build( );
        messageRepository.save( message1 );
        messageRepository.save( message2 );
        messageRepository.save( message3 );
        
        List<Message> messageList = messageRepository.getAllMessages( room );
        
        assertNotNull( messageList );
        assertEquals( messageList.size( ), 3 );
    }
    
    @Test
    public void shouldReturnEmptyMessageList( ) {
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
        
        List<Message> messageList = messageRepository.getAllMessages( room );
        
        assertEquals( messageList.size( ), 0 );
    }
}

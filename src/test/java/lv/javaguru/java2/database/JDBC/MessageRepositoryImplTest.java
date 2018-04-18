package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.domain.builders.MessageBuilder.createMessage;
import static lv.javaguru.java2.domain.builders.RoomBuilder.createRoom;
import static org.junit.Assert.*;

public class MessageRepositoryImplTest {
    
    private MessageRepositoryImpl messageRepository = new MessageRepositoryImpl( );
    private RoomRepositoryImpl roomRepository = new RoomRepositoryImpl( );
    private Message message;
    private Room room;
    
    @Before
    public void init( ) {
        room = createRoom( )
                .withName( "TestRoom" )
                .withCreatorNickname( "TestUser" )
                .build( );
        roomRepository.save( room );
        System.out.println( "Room " + room.getName( ) + " successfully added to database." );
        message = createMessage( )
                .withMessageBody( "Hello, World!" )
                .withUserNickname( "TestUser" )
                .withRoom( room )
                .build( );
        messageRepository.save( message );
        System.out.println( "Message '" + message.getMessage_body( ) + "' successfully added to database." );
    }
    
    @Test
    public void shouldAddMessage( ) {
        System.out.println( "shouldAddRoom" );
        assertNotNull( message.getId( ) );
        System.out.println( "Message id is: " + message.getId( ) );
    }
    
    @Test
    public void shouldReturnAListOfMessagesForGivenRoom( ) {
        System.out.println( "shouldReturnAListOfMessagesForGivenRoom" );
        Message message2 = createMessage( )
                .withMessageBody( "Message 2" )
                .withUserNickname( "TestUser" )
                .withRoom( room )
                .build( );
        Message message3 = createMessage( )
                .withMessageBody( "Message 3" )
                .withUserNickname( "TestUser" )
                .withRoom( room )
                .build( );
        messageRepository.save( message2 );
        messageRepository.save( message3 );
        
        List<Message> messageList = messageRepository.getAllMessages( room );
        if ( !messageList.isEmpty( ) ) {
            messageList.forEach( System.out::println );
        }
        assertNotNull( messageList );
        assertEquals( messageList.size( ), 3 );
        
        // cleanup
        if(message2 != null) {
            messageRepository.deleteMessage( message2 );
        }
        if(message3 != null){
            messageRepository.deleteMessage( message3 );
        }
    }
    
    @After
    public void cleanDatabase( ) {
        roomRepository.deleteRoom( room );
        System.out.println( "Room " + room.getName( ) + " successfully deleted from database." );
        messageRepository.deleteMessage( message );
        System.out.println( "Message '" + message.getMessage_body( ) + "' successfully deleted from database.\n\n" );
    }
}

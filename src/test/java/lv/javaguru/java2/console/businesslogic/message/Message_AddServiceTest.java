package lv.javaguru.java2.console.businesslogic.message;

import lv.javaguru.java2.console.database.MessageRepository;
import lv.javaguru.java2.console.domain.Message;
import lv.javaguru.java2.console.domain.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class Message_AddServiceTest {
    
    @Mock MessageRepository messageRepository;
    
    @InjectMocks
    Message_AddService messageAddService = new Message_AddService( );
    
    @Test
    public void shouldAddMessageToDatabase( ) {
        String messageText = "Hello, World!";
        String userNickname = "TestNickname";
        Room room = Mockito.mock( Room.class );
        
        Message_AddResponse response = messageAddService.addMessage( messageText, userNickname, room );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getMessage( ) );
        assertNull( response.getErrors( ) );
    }
}

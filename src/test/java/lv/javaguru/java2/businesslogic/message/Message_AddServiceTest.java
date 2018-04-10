package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Message;
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
    
    @Mock Database database;
    
    @InjectMocks
    Message_AddService messageAddService = new Message_AddService( );
    
    @Test
    public void shouldAddMessageToDatabase( ) {
        String message = "Hello, World!";
        String nickname = "TestNickname";
        Long roomId = Integer.toUnsignedLong( 1 );
        Message msg = Mockito.mock( Message.class );
        Mockito.when( database.message_add( message, nickname, roomId ) )
                .thenReturn( msg );
        
        Message_AddResponse response = messageAddService.addMessage( message, nickname, roomId );
        
        assertTrue( response.isSuccess( ) );
        assertNotNull( response.getMessage( ) );
        assertNull( response.getErrors( ) );
    }
}

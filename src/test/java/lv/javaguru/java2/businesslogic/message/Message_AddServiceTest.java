package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class Message_AddServiceTest {
    
    @Autowired private Message_AddService messageAddService;
    @Autowired private Database database;
    private final static String roomName = "GuestRoom";
    private static Long roomId;
    
    @Before
    public void init( ) {
        // Setup room in database
        Optional<Room> search = database.chatRoom_get( roomName );
        if ( search.isPresent( ) ) {
            roomId = search.get( ).getId( );
        } else {
            String creatorNickname = "TestCreatorNickname";
            Room room = database.chatRoom_add( roomName, creatorNickname );
            if ( room != null ) {
                roomId = room.getId( );
            } else {
                System.out.println( "Cannot create chat room for test." );
                System.exit( 0 );
            }
        }
    }
    
    @Test
    public void shouldAddMessageToDatabase( ) {
        String message = "Hello, World!";
        String nickname = "TestNickname";
        
        Message_AddResponse response = messageAddService.addMessage( message, nickname, roomId );
        
        assertTrue( response.isSuccess( ) );
    }
}

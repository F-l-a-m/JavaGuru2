package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.database.MessageRepository;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class ChatController implements MVCController {
    
    @Autowired private MessageRepository messageRepository;
    @Autowired private RoomRepository roomRepository;
    
    @Override
    public MVCModel processGet( HttpServletRequest request ) {
        List<Message> messageList = getMessageList( );
        if ( messageList.isEmpty( ) ) {
            return new MVCModel( "error.jsp", null );
        } else {
            return new MVCModel( "/chat.jsp", messageList );
        }
    }
    
    @Override
    public MVCModel processPost( HttpServletRequest request ) {
        throw new UnsupportedOperationException( );
    }
    
    @Transactional
    private List<Message> getMessageList( ) {
        Optional<Room> optionalRoom = roomRepository.get( "GuestRoom" );
        if ( !optionalRoom.isPresent( ) ) {
            return null;
        } else {
            List<Message> messageList = messageRepository.getAllMessages( optionalRoom.get( ) );
            return messageList;
        }
    }
}

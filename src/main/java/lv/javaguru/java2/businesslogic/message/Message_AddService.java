package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.database.MessageDAO;
import lv.javaguru.java2.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Message_AddService {
    
    @Autowired private MessageDAO messageDAO;
    
    @Transactional
    public Message_AddResponse addMessage( String message, String nickname, Long roomId ) {
        // Add checks for message, user, room. Only then add.
        Message msg = messageDAO.add( message, nickname, roomId );
        return new Message_AddResponse( true, msg, null );
    }
}

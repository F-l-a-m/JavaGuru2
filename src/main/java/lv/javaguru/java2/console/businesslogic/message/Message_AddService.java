package lv.javaguru.java2.console.businesslogic.message;

import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.database.MessageRepository;
import lv.javaguru.java2.console.domain.Message;
import lv.javaguru.java2.console.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static lv.javaguru.java2.console.domain.builders.MessageBuilder.createMessage;

@Component
public class Message_AddService {
    
    @Autowired private MessageRepository messageRepository;
    
    @Transactional
    public Message_AddResponse addMessage( String messageBody, String nickname, Room room ) {
        // Add checks for message body, user, room. Only then save.
        Message message = createMessage( )
                .withMessageBody( messageBody )
                .withUserNickname( nickname )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .withRoom( room )
                .build( );
        messageRepository.save( message );
        
        return new Message_AddResponse( true, message, null );
    }
}

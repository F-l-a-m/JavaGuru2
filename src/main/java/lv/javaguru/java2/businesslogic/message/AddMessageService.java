package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddMessageService {
    
    @Autowired private Database database;
    
    @Transactional
    public AddMessageResponse addMessage( String message, String nickname, Long roomId ) {
        Message msg = database.message_add( message, nickname, roomId );
        return new AddMessageResponse( true, msg, null );
    }
}

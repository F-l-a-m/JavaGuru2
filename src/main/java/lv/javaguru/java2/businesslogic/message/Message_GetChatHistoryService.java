package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class Message_GetChatHistoryService {
    
    @Autowired private Database database;
    
    @Transactional
    public Message_GetChatHistoryResponse go( Room room ) {
        @SuppressWarnings (value="unchecked")
        List<Message> chatHistory = database.message_getAllMessages( room.getId( ) );
        return new Message_GetChatHistoryResponse( chatHistory );
    }
}

package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.database.MessageRepository;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class Message_GetChatHistoryService {
    
    @Autowired private MessageRepository messageRepository;
    
    @Transactional
    public Message_GetChatHistoryResponse go( Room room ) {
        List<Message> chatHistory = messageRepository.getAllMessages( room );
        return new Message_GetChatHistoryResponse( chatHistory );
    }
}

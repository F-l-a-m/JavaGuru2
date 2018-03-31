package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetAllChatHistoryService {
    
    @Autowired private Database database;
    
    @Transactional
    public GetAllChatHistoryResponse go( Room room ) {
        @SuppressWarnings (value="unchecked")
        List<Message> chatHistory = database.getAllChatHistoryInRoom( room.getId( ) );
        return new GetAllChatHistoryResponse( chatHistory );
    }
}

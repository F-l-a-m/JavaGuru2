package lv.javaguru.java2.businesslogic.message.getChatHistory;

import lv.javaguru.java2.domain.Room;

public interface Message_GetChatHistoryService {
    
    Message_GetChatHistoryResponse getChatHistoryForRoom( Room room );
    
}

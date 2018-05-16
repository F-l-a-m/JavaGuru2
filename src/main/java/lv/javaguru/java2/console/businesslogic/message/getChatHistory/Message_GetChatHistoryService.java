package lv.javaguru.java2.console.businesslogic.message.getChatHistory;

import lv.javaguru.java2.console.domain.Room;

public interface Message_GetChatHistoryService {
    
    Message_GetChatHistoryResponse getChatHistoryForRoom( Room room );
    
}

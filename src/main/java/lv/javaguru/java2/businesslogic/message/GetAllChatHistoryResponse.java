package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.domain.Message;

import java.util.List;

public class GetAllChatHistoryResponse {
    
    private List<Message> chatHistory;
    
    public GetAllChatHistoryResponse( List<Message> chatHistory ) {
        this.chatHistory = chatHistory;
    }
    
    public List<Message> getChatHistory( ) {
        return chatHistory;
    }
}

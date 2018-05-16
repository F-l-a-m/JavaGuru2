package lv.javaguru.java2.console.businesslogic.message.getChatHistory;

import lv.javaguru.java2.console.domain.Message;

import java.util.List;

public class Message_GetChatHistoryResponse {
    
    private List<Message> chatHistory;
    
    public Message_GetChatHistoryResponse( List<Message> chatHistory ) {
        this.chatHistory = chatHistory;
    }
    
    public List<Message> getChatHistory( ) {
        return chatHistory;
    }
}

package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.businesslogic.models.ChatLine;
import lv.javaguru.java2.database.ChatDatabase;

public class GetChatMessageService {

    private ChatDatabase chatDatabase;

    public GetChatMessageService(ChatDatabase chatDatabase) {
        this.chatDatabase = chatDatabase;
    }

    public ChatLine GetLastMessageFromDB(){
        return chatDatabase.GetLastMessage();
    }
}

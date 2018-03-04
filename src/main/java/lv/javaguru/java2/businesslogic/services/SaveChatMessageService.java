package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.database.ChatDatabase;
import lv.javaguru.java2.businesslogic.models.ChatLine;

public class SaveChatMessageService {

    private ChatDatabase chatDatabase;

    public SaveChatMessageService(ChatDatabase chatDatabase) {
        this.chatDatabase = chatDatabase;
    }

    public void SaveMessageToDatabase(ChatLine line){
        chatDatabase.add(line);
    }
}

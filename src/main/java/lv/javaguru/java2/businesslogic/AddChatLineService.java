package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ChatDatabase;
import lv.javaguru.java2.models.ChatLine;

public class AddChatLineService {

    private ChatDatabase chatDatabase;

    public AddChatLineService(ChatDatabase chatDatabase) {
        this.chatDatabase = chatDatabase;
    }

    public void addChatLine(ChatLine line){
        chatDatabase.add(line);
    }
}

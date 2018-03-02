package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.database.ChatDatabase;
import lv.javaguru.java2.businesslogic.models.ChatLine;

public class SaveMessage {

    private ChatDatabase chatDatabase;

    public SaveMessage(ChatDatabase chatDatabase) {
        this.chatDatabase = chatDatabase;
    }

    public void SaveMessageToDB(ChatLine line){
        chatDatabase.add(line);
    }
}

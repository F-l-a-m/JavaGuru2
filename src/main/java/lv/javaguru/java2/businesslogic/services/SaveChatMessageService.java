package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.businesslogic.models.ChatLine;

public class SaveChatMessageService {

    private Database database;

    public SaveChatMessageService(Database database) {
        this.database = database;
    }

    public void SaveMessageToDatabase(ChatLine line){
        database.addChatLine(line);
    }
}

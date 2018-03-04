package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.businesslogic.models.ChatLine;
import lv.javaguru.java2.database.Database;

public class GetChatMessageService {

    private Database database;

    public GetChatMessageService(Database database) {
        this.database = database;
    }

    public ChatLine GetLastMessageFromDB(){
        return database.getLastChatMessage();
    }
}

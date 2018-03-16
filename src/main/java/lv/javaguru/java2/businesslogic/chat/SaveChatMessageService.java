package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.database.Database;

public class SaveChatMessageService {

    private Database database;

    public SaveChatMessageService(Database database) {
        this.database = database;
    }

    public void SaveMessageToDatabase(Message line){
        database.addChatLine(line);
    }
}

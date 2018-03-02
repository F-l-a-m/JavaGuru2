package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.SaveMessage;
import lv.javaguru.java2.database.ChatDatabase;
import lv.javaguru.java2.Globals;

public class AddChatLineView implements View {

    private SaveMessage saveMessage;

    public AddChatLineView(ChatDatabase database) {
        this.saveMessage = new SaveMessage(database);
    }

    @Override
    public void execute() {
        System.out.println(Globals.getLine());
        saveMessage.SaveMessageToDB(Globals.getLine());
    }
}

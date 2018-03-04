package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.services.GetChatMessageService;
import lv.javaguru.java2.database.ChatDatabase;

public class PrintLastChatLineView implements View {

    private ChatDatabase database;
    private GetChatMessageService getChatMessageService;

    public PrintLastChatLineView(ChatDatabase database) {
        this.database = database;
        this.getChatMessageService = new GetChatMessageService(database);
    }

    @Override
    public void execute() {
        System.out.println(getChatMessageService.GetLastMessageFromDB());
    }
}

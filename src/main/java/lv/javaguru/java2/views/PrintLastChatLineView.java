package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.services.GetChatMessageService;
import lv.javaguru.java2.database.Database;

public class PrintLastChatLineView implements View {

    private GetChatMessageService getChatMessageService;

    public PrintLastChatLineView(Database database) {
        this.getChatMessageService = new GetChatMessageService(database);
    }

    @Override
    public void execute() {
        System.out.println(getChatMessageService.GetLastMessageFromDB());
    }
}

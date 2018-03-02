package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.AddChatLineService;
import lv.javaguru.java2.database.ChatDatabase;
import lv.javaguru.java2.GlobalLine;

public class AddChatLineView implements View {

    private AddChatLineService addChatLineService;

    public AddChatLineView(ChatDatabase database) {
        this.addChatLineService = new AddChatLineService(database);
    }

    @Override
    public void execute() {
        addChatLineService.addChatLine(GlobalLine.getLine());
        System.out.println(GlobalLine.getLine());
    }
}

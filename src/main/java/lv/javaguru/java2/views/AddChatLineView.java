package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.AddChatLineService;
import lv.javaguru.java2.database.ChatDatabase;
import lv.javaguru.java2.models.ChatLine;

public class AddChatLineView implements View {

    private AddChatLineService addChatLineService;
    private ChatLine line;

    public AddChatLineView(ChatDatabase database, ChatLine line) {
        this.addChatLineService = new AddChatLineService(database);
        this.line = line;
    }

    @Override
    public void execute() {
        addChatLineService.addChatLine(line);
    }
}

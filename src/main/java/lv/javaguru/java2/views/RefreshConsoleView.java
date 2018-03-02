package lv.javaguru.java2.views;

import lv.javaguru.java2.database.ChatDatabase;
import lv.javaguru.java2.businesslogic.models.ChatLine;

public class RefreshConsoleView implements View {

    private ChatDatabase database;

    public RefreshConsoleView(ChatDatabase database) {
        this.database = database;
    }

    @Override
    public void execute() {
        // Clear console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                +"\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
        );
        // Print whole chat history
        for(ChatLine line : database.getAllChat()){
            System.out.println(line);
        }
    }
}

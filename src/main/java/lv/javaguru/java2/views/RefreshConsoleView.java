package lv.javaguru.java2.views;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.businesslogic.chat.ChatLine;

public class RefreshConsoleView implements View {

    private Database database;

    public RefreshConsoleView(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        // Clear console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // Print whole chat history
        for(ChatLine line : database.getAllChat()){
            System.out.println(line);
        }
    }
}

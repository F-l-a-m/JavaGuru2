package lv.javaguru.java2.views;

import lv.javaguru.java2.database.Database;

public class PrintLastChatLineView implements View {

    private Database database;

    public PrintLastChatLineView(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        System.out.println(database.getLastChatMessage());
    }
}

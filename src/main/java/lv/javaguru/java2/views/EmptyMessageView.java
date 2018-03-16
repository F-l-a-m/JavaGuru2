/*
package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.Message;
import lv.javaguru.java2.businesslogic.chat.Timestamp;
import lv.javaguru.java2.database.Database;

public class EmptyMessageView implements View {

    private Database database;
    private Timestamp timestamp = new Timestamp();

    public EmptyMessageView(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Message emptyLine = new Message(
                timestamp.getTimestamp(),
                database.getCurrentUser().getNickname(),
                ""
                );

        System.out.println(emptyLine);
    }
}
*/

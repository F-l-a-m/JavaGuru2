package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.MessageService;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.database.Database;

public class PrintMessageView implements View {

    private final MessageService messageService;

    public PrintMessageView(Database database) {
        messageService = new MessageService(database);
    }

    @Override
    public void execute() {
        System.out.println(messageService.getLastChatMessageInARoom(CurrentRoom.getRoom()));
    }
}

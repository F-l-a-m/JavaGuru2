package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.MessageService;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.businesslogic.chat.Message;

import java.util.List;

public class RefreshConsoleView implements View {

    private MessageService messageService;

    public RefreshConsoleView(Database database) {
        this.messageService = new MessageService(database);
    }

    @Override
    public void execute() {
        // Clear console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // Print whole chat history in current chat room
        List<Message> messages = messageService.getAllChatHistoryInRoom(CurrentRoom.getRoom());
        for(Message m : messages){
            System.out.println(m);
        }
    }
}

package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.MessageService;
import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.domain.Message;

import java.util.List;

public class RefreshConsoleView implements View {

    private MessageService messageService;
    private ActiveRoom activeRoom;

    public RefreshConsoleView(MessageService messageService, ActiveRoom activeRoom) {
        this.messageService = messageService;
        this.activeRoom = activeRoom;
    }

    @Override
    public void execute() {
        // Clear console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // Print whole chat history in current chat room
        List<Message> messages = messageService.getAllChatHistoryInRoom(activeRoom.getRoom().getName());
        for(Message m : messages){
            System.out.println(m);
        }
    }
}

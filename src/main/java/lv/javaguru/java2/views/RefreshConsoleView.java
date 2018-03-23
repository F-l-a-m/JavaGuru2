package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.MessageService;
import lv.javaguru.java2.domain.Message;

import java.util.List;

public class RefreshConsoleView implements View {

    private final MessageService messageService;
    private final String roomName;

    public RefreshConsoleView(MessageService messageService, String roomName) {
        this.messageService = messageService;
        this.roomName = roomName;
    }

    @Override
    public void execute() {
        // Clear console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // Print whole chat history in current chat room
        List<Message> messages = messageService.getAllChatHistoryInRoom(roomName);
        for(Message m : messages){
            System.out.println(m);
        }
    }
}

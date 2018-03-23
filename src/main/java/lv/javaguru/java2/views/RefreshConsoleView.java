/*
package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.message.MessageService;
import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RefreshConsoleView implements View {

    @Autowired private MessageService messageService;
    private ActiveRoom activeRoom;

    public RefreshConsoleView(ActiveRoom activeRoom) {
        this.activeRoom = activeRoom;
    }

    @Override
    public void execute() {
        // Clear console
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        // Print whole message history in current message room
        List<Message> messages = messageService.getAllChatHistoryInRoom(activeRoom.getRoom().getName());
        for(Message m : messages){
            System.out.println(m);
        }
    }
}
*/

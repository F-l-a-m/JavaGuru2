package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.message.GetAllChatHistoryResponse;
import lv.javaguru.java2.businesslogic.message.GetAllChatHistoryService;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/*
@Component
public class PrintChatHistoryInRoomView implements View {
    
    private Room room;
    @Autowired private GetAllChatHistoryService getAllChatHistoryService;
    
    public PrintChatHistoryInRoomView( Room room ) {
        this.room = room;
    }
    
    @Override
    public void execute( ) {
        // Clear console
        System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" );
        
        // Print whole message history in given chat room
        GetAllChatHistoryResponse response = getAllChatHistoryService.go( room );
        List<Message> messages = response.getChatHistory( );
        messages.forEach( System.out::println );
    }
}
*/

package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.businesslogic.message.getChatHistory.Message_GetChatHistoryResponse;
import lv.javaguru.java2.businesslogic.message.getChatHistory.Message_GetChatHistoryService;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintChatHistoryView implements View {

    @Autowired private Message_GetChatHistoryService getChatHistoryService;
    @Autowired private ConsoleSession session;

    @Override
    public void execute( ) {
        Room room = session.getRoom( );
        Message_GetChatHistoryResponse response = getChatHistoryService.getChatHistoryForRoom( room );
        List<Message> messages = response.getChatHistory( );
        if ( !messages.isEmpty( ) ) {
            System.out.println( "\n\n\n\n\n\n\n\n" ); // Clear console :)
            messages.forEach( System.out::println );
        }
    }
}

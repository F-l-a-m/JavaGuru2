package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.businesslogic.message.Message_AddResponse;
import lv.javaguru.java2.businesslogic.message.Message_AddService;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintChatMessageView implements View {
    
    @Autowired private Message_AddService messageAddService;
    @Autowired private ConsoleSession session;
    
    @Override
    public void execute( ) {
        
        User user = session.getUser( );
        Room room = session.getRoom( );
        String userConsoleInput = session.getUserConsoleInputResponse( ).getData( );
        
        
        Message_AddResponse response = messageAddService.addMessage(
                userConsoleInput,
                user.getNickname( ),
                room
        );
        if ( response.isSuccess( ) ) {
            System.out.println( response.getMessage( ) );
        } else {
            printErrors( response.getErrors( ) );
        }
    }
}

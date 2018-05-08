package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.businesslogic.userInRoom.Room_JoinOrCreateResponse;
import lv.javaguru.java2.businesslogic.userInRoom.Room_JoinOrCreateService;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinChatRoomView implements View {
    
    @Autowired private Room_JoinOrCreateService joinOrCreateService;
    @Autowired private ConsoleSession session;
    
    @Override
    public void execute( ) {

        String roomNameFromUserInput = session.getUserConsoleInputResponse( ).getData( );
        User user = session.getUser( );

        Room_JoinOrCreateResponse response =
                joinOrCreateService.joinOrCreateRoom( roomNameFromUserInput, user );
        if ( response.isSuccess( ) ) {
            Room room = response.getRoom( );
            session.setRoom( room );
            System.out.println( "\nYou are now chatting in '" + room.getName( ) + "'" );
        } else {
            printErrors( response.getErrors( ) );
        }
    }
}

package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.console.businesslogic.userInRoom.Room_JoinOrCreateResponse;
import lv.javaguru.java2.console.businesslogic.userInRoom.Room_JoinOrCreateService;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinDefaultRoomView implements View {
    
    @Autowired private Room_JoinOrCreateService joinOrCreateService;
    @Autowired private ConsoleSession session;
    
    @Override
    public void execute( ) {
        
        User user = session.getUser( );
        
        Room_JoinOrCreateResponse response =
                joinOrCreateService.joinOrCreateRoom( "GuestRoom", user );
        if ( response.isSuccess( ) ) {
            Room room = response.getRoom( );
            session.setRoom( room );
            System.out.println( "\nYou are now chatting in '" + room.getName( ) + "'" );
        } else {
            printErrors( response.getErrors( ) );
        }
    }
}

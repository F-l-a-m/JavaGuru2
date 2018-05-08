package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.businesslogic.userInRoom.User_RemoveFromRoomResponse;
import lv.javaguru.java2.businesslogic.userInRoom.User_RemoveFromRoomService;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeaveRoomView implements View {
    
    @Autowired private User_RemoveFromRoomService removeFromRoomService;
    @Autowired private ConsoleSession session;
    
    @Override
    public void execute( ) {
        User user = session.getUser( );
        Room room = session.getRoom( );
        
        User_RemoveFromRoomResponse response =
                removeFromRoomService.leaveRoom( user.getNickname( ), room.getName( ) );
        if ( response.isSuccess( ) ) {
            System.out.println( "User " + user.getNickname( ) + " left " + room.getName( ) );
            room = response.getRoom( );
            session.setRoom( room );
            System.out.println( "You are now chatting in " + room.getName( ) );
        } else {
            printErrors( response.getErrors( ) );
        }
    }
}

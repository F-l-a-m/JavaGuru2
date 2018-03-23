/*
package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeaveChatRoomView implements View {
    
    @Autowired private UserService userService;
    private User user;
    private ActiveRoom activeRoom;
    
    public LeaveChatRoomView(
            User user,
            ActiveRoom activeRoom
    ) {
        this.user = user;
        this.activeRoom = activeRoom;
    }
    
    @Override
    public void execute( ) {
        userService.removeUserFromChatRoom(user, activeRoom);
    }
}
*/

package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.user.UserService;

public class LeaveChatRoomView implements View {
    
    private UserService userService;
    private User user;
    private ActiveRoom activeRoom;
    
    public LeaveChatRoomView(
            User user,
            UserService userService,
            ActiveRoom activeRoom
    ) {
        this.userService = userService;
        this.user = user;
        this.activeRoom = activeRoom;
    }
    
    @Override
    public void execute( ) {
        userService.removeUserFromChatRoom(user, activeRoom);
    }
}

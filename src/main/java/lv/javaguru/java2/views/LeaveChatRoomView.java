package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.Database;

public class LeaveChatRoomView implements View {
    
    private final UserService userService;
    private final User user;
    
    public LeaveChatRoomView(Database database, User user, UserService userService) {
        this.userService = userService;
        this.user = user;
    }
    
    @Override
    public void execute( ) {
        userService.removeUserFromChatRoom(user, CurrentRoom.getRoom().getName());
    }
}

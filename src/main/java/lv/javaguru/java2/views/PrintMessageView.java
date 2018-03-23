package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.chat.MessageService;
import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.User;

public class PrintMessageView implements View {

    private User user;
    private UserService userService;
    private ActiveRoom activeRoom;
    private MessageService messageService;

    public PrintMessageView(
            User user,
            UserService userService,
            ActiveRoom activeRoom,
            MessageService messageService
    ) {
        this.user = user;
        this.userService = userService;
        this.activeRoom = activeRoom;
        this.messageService = messageService;
    }

    @Override
    public void execute() {
        String input = userService.getUserInput(user);
        Message msg = messageService.saveMessageToDatabase(input, user, activeRoom.getRoom());
        System.out.println(msg);
    }
}

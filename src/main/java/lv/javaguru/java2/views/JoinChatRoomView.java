package lv.javaguru.java2.views;

import lv.javaguru.java2.domain.ChatRoom;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.user.UserService;

public class JoinChatRoomView implements View {
    
    private User user;
    private UserService userService;
    private ChatRoomService chatRoomService;

    public JoinChatRoomView(
            User user,
            UserService userService,
            ChatRoomService chatRoomService
    ) {
        this.user = user;
        this.userService = userService;
        this.chatRoomService = chatRoomService;
    }

    @Override
    public void execute() {
        String roomName = userService.getUserInput(user);
        ChatRoom room = chatRoomService.findChatRoomByName(roomName);
        if(room != null) {
            // join
            userService.addUserToChatRoom(user, roomName);
        }
        else {
            // create new room and add user to it
            chatRoomService.createNewChatRoom(roomName, user.getNickname() );
            room = userService.addUserToChatRoom(user, roomName);
            System.out.println("Successfully joined " + roomName);
        }
    }
}

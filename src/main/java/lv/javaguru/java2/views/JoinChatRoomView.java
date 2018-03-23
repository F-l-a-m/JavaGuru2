package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.domain.ChatRoom;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.user.UserService;

import java.util.Optional;

public class JoinChatRoomView implements View {
    
    private User user;
    private UserService userService;
    private ChatRoomService chatRoomService;
    private ActiveRoom activeRoom;

    public JoinChatRoomView(
            User user,
            UserService userService,
            ChatRoomService chatRoomService,
            ActiveRoom activeRoom
    ) {
        this.user = user;
        this.userService = userService;
        this.chatRoomService = chatRoomService;
        this.activeRoom = activeRoom;
    }

    @Override
    public void execute() {
        String roomName = userService.getUserInput(user);
        Optional<ChatRoom> newRoom = chatRoomService.findChatRoomByName(roomName);
        if(newRoom.isPresent()) {
            // join
            userService.addUserToChatRoom(user, roomName);
            activeRoom.setRoom(newRoom.get());
        } else {
            // create new oldRoom and add user to it
            ChatRoom room = chatRoomService.createNewChatRoom(roomName, user.getNickname() );
            activeRoom.setRoom(room);
            userService.addUserToChatRoom(user, roomName);
            System.out.println("Successfully joined " + roomName);
        }
    }
}

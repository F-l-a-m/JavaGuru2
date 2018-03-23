/*
package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JoinChatRoomView implements View {
    
    private User user;
    @Autowired private UserService userService;
    @Autowired private ChatRoomService chatRoomService;
    private ActiveRoom activeRoom;

    public JoinChatRoomView(
            User user,
            ActiveRoom activeRoom
    ) {
        this.user = user;
        this.activeRoom = activeRoom;
    }

    @Override
    public void execute() {
        String roomName = userService.getUserInput(user);
        Optional<Room> newRoom = chatRoomService.findChatRoomByName(roomName);
        if(newRoom.isPresent()) {
            // join
            userService.addUserToChatRoom(user, roomName);
            activeRoom.setRoom(newRoom.get());
        } else {
            // create new oldRoom and add user to it
            Room room = chatRoomService.createNewChatRoom(roomName, user.getNickname() );
            activeRoom.setRoom(room);
            userService.addUserToChatRoom(user, roomName);
            System.out.println("Successfully joined " + roomName);
        }
    }
}
*/

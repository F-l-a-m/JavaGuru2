package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.Database;

public class JoinChatRoomView implements View {

    private final ChatRoomService chatRoomService;
    private final UserService userService;
    private final StringCache stringCache;
    private final User user;

    public JoinChatRoomView(Database database, User user, StringCache stringCache) {
        this.stringCache = stringCache;
        this.chatRoomService = new ChatRoomService(database);
        this.userService = new UserService(database);
        this.user = user;
    }

    @Override
    public void execute() {
        String roomName = stringCache.getTemporaryString();
        ChatRoom room = chatRoomService.findChatRoomByName(roomName);
        if(room != null) {
            // join
            userService.addUserToChatRoom(user, roomName);
            CurrentRoom.setRoom(room);
        }
        else {
            // create new room and add user to it
            chatRoomService.createNewChatRoom(roomName);
            room = userService.addUserToChatRoom(user, roomName);
            CurrentRoom.setRoom(room);
            System.out.println("Successfully joined " + roomName);
        }
    }
}

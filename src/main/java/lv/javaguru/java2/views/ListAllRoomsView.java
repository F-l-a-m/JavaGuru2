package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class ListAllRoomsView implements View {
    
    private final ChatRoomService chatRoomService;
    
    public ListAllRoomsView(Database database) {
        chatRoomService = new ChatRoomService(database);
    }
    
    @Override
    public void execute( ) {
        List<ChatRoom> rooms = chatRoomService.getListOfAllChatRooms();
        for(ChatRoom r : rooms) {
            System.out.print(r + ", ");
        }
    }
}

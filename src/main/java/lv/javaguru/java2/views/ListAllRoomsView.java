/*
package lv.javaguru.java2.views;

import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAllRoomsView implements View {
    
    @Autowired private ChatRoomService chatRoomService;

    @Override
    public void execute( ) {
        List<Room> rooms = chatRoomService.getListOfAllChatRooms();
        for(Room r : rooms) {
            System.out.print(r + ", ");
        }
    }
}
*/

package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.room.Room_GetAllResponse;
import lv.javaguru.java2.console.businesslogic.room.Room_GetAllService;
import lv.javaguru.java2.console.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListAllRoomsView implements View {
    
    @Autowired private Room_GetAllService roomListAllService;
    
    @Override
    public void execute( ) {
        Room_GetAllResponse response = roomListAllService.getStringList( );
        if ( response.isSuccess( ) ) {
            System.out.println( "List of all available chat rooms:" );
            List<String> roomList = response.getListOfAllRooms( );
            roomList.forEach( System.out::println );
        }
    }
}

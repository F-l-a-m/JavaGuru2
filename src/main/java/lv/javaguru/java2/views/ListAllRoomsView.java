package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.Room_ListAllResponse;
import lv.javaguru.java2.businesslogic.room.Room_ListAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListAllRoomsView implements View {
    
    @Autowired private Room_ListAllService roomListAllService;

    @Override
    public void execute( ) {
        Room_ListAllResponse roomListAllResponse = roomListAllService.list( );
        if( roomListAllResponse.isSuccess( )) {
            System.out.print("List of all available chat rooms: ");
            int size = roomListAllResponse.getListOfAllRooms().size();
            for(int i = 0; i < size - 2 ; i++) {
                String s = roomListAllResponse.getListOfAllRooms().get(i);
                System.out.print(s + ", ");
            }
            System.out.print( roomListAllResponse.getListOfAllRooms().get(size - 1) + '.');
        }
    }
}

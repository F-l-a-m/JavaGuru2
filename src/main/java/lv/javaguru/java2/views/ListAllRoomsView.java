package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.ListAllRoomsResponse;
import lv.javaguru.java2.businesslogic.room.ListAllRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListAllRoomsView implements View {
    
    @Autowired private ListAllRoomsService listAllRoomsService;

    @Override
    public void execute( ) {
        ListAllRoomsResponse listAllRoomsResponse = listAllRoomsService.list( );
        if(listAllRoomsResponse.isSuccess( )) {
            System.out.print("List of all available chat rooms: ");
            int size = listAllRoomsResponse.getListOfAllRooms().size();
            for(int i = 0; i < size - 2 ; i++) {
                String s = listAllRoomsResponse.getListOfAllRooms().get(i);
                System.out.print(s + ", ");
            }
            System.out.print(listAllRoomsResponse.getListOfAllRooms().get(size - 1) + '.');
        }
    }
}

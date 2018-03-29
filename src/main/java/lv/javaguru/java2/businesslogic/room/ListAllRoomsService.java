package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListAllRoomsService {

    @Autowired Database database;

    public ListAllRoomsResponse list( ){
        List<String> listOfAllRooms = new ArrayList<>( );
        database.getListOfAllRooms( ).forEach(x -> {
            listOfAllRooms.add( x.getName( ) );
        });
        if(!listOfAllRooms.isEmpty())
            return new ListAllRoomsResponse(listOfAllRooms, true);
        return new ListAllRoomsResponse(null, false);
    }
}
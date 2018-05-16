package lv.javaguru.java2.console.businesslogic.room;

import java.util.List;

public class Room_GetAllResponse {

    private List listOfAllRooms;
    boolean isSuccess;

    public Room_GetAllResponse( List listOfAllRooms, boolean isSuccess ) {
        this.listOfAllRooms = listOfAllRooms;
        this.isSuccess = isSuccess;
    }

    public List getListOfAllRooms( ) {
        return listOfAllRooms;
    }

    public boolean isSuccess( ) {
        return isSuccess;
    }
}

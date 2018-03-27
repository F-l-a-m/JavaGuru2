package lv.javaguru.java2.businesslogic.room;

import java.util.List;

public class ListAllRoomsResponse {

    private List<String> listOfAllRooms;
    boolean isSuccess;

    public ListAllRoomsResponse( List<String> listOfAllRooms, boolean isSuccess ) {
        this.listOfAllRooms = listOfAllRooms;
        this.isSuccess = isSuccess;
    }

    public List<String> getListOfAllRooms( ) {
        return listOfAllRooms;
    }

    public boolean isSuccess( ) {
        return isSuccess;
    }
}


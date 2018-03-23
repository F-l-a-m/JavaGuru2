package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.domain.Room;

import java.util.List;

public class AddRoomResponse {

    private boolean success;
    private Room room;
    private List<Error> errors;


    public AddRoomResponse(boolean success, Room room, List<Error> errors) {
        this.success = success;
        this.errors = errors;
        this.room = room;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

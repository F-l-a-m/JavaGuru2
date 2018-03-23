/*
package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChatRoomService {

    private final Database database;

    public ChatRoomService(Database database) {
        this.database = database;
    }

    public Room initializeGuestRoom(String nickname) {
        // if not found in database, then create new row
        Optional<Room> foundRoom;
        foundRoom = database.findChatRoomByRoomName("Guest room");
        if(!foundRoom.isPresent()) {
            Optional<Room> newRoom;
            newRoom = database.createNewChatRoom("Guest room", nickname);
            System.out.println("Guest room created");
            System.out.println("Now chatting in \'Guest room\'");
            return newRoom.get();
        }
        else {
            System.out.println("Guest room is already created");
            System.out.println("Now chatting in \'Guest room\'");
            return foundRoom.get();
        }
    }

    public Room createNewChatRoom(String roomName, String creatorNickname) {
        Optional<Room> room = database.createNewChatRoom(roomName, creatorNickname);
        return room.orElse(null);
    }

    public Optional<Room> findChatRoomByName(String roomName) {
        Optional<Room> room = database.findChatRoomByRoomName(roomName);
        if(room.isPresent()){
            return room;
        }
        else {
            System.out.println("Room " + roomName + " not found");
            return Optional.empty();
        }
    }

    public List<Room> getListOfAllChatRooms() {
        List<Room> rooms = database.getListOfAllRooms();
        if(rooms.isEmpty()){
            System.out.println("No message rooms found in database");
            return null;
        }
        else {
            return rooms;
        }
    }
}
*/

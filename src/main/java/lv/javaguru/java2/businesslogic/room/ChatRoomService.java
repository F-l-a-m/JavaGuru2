package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.ChatRoom;
import lv.javaguru.java2.domain.User;

import java.util.List;
import java.util.Optional;

public class ChatRoomService {

    private final Database database;

    public ChatRoomService(Database database) {
        this.database = database;
    }

    public ChatRoom initializeGuestRoom(String nickname) {
        // if not found in database, then create new row
        Optional<ChatRoom> foundRoom;
        foundRoom = database.findChatRoomByRoomName("Guest room");
        if(!foundRoom.isPresent()) {
            Optional<ChatRoom> newRoom;
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

    public ChatRoom createNewChatRoom(String roomName, String creatorNickname) {
        Optional<ChatRoom> room = database.createNewChatRoom(roomName, creatorNickname);
        return room.orElse(null);
    }

    public ChatRoom findChatRoomByName(String roomName) {
        Optional<ChatRoom> foundRoom = database.findChatRoomByRoomName(roomName);
        if(foundRoom.isPresent()){
            return foundRoom.get();
        }
        else {
            System.out.println("Room " + roomName + " not found");
        }
        return null;
    }

    public List<ChatRoom> getListOfAllChatRooms() {
        List<ChatRoom> chatRooms = database.getListOfAllRooms();
        if(chatRooms.isEmpty()){
            System.out.println("No chat rooms found in database");
            return null;
        }
        else {
            return chatRooms;
        }
    }
}

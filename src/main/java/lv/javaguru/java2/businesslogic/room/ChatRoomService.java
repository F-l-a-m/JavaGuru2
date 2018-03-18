package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatRoomService {

    private Database database;

    public ChatRoomService(Database database) {
        this.database = database;
    }

    public void initializeGuestRoom() {
        ChatRoom guestRoom = new ChatRoom("Guest room");
        // if not found in database, then create new row
        Optional<ChatRoom> foundRoom;
        foundRoom = database.findChatRoom("Guest room");
        if(!foundRoom.isPresent()) {
            database.createNewChatRoom("Guest room");
            System.out.println("Guest room created");
        }
        else {
            System.out.println("Guest room is already created");
        }
    }




    /*public Response join(Database database, String roomName, User userToJoin){
        // validate room name here

        List<Error> errors = new ArrayList<>();

        Optional<ChatRoom> findChatRoom = database.findChatRoom(roomName);
        if(findChatRoom.isPresent()){
            // join room
            ChatRoom chatRoom = findChatRoom.get();
            chatRoom.join(userToJoin);
            return new Response(true, null);
        }
        else {
            ChatRoom newRoom = new ChatRoom(userToJoin, roomName);
            database.addToRoomList(newRoom);
            return new Response(true, null);
        }
        //return new Response(false, errors); // if roomname not valid
    }*/
}

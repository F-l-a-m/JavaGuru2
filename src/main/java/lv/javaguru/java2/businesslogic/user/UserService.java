package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.database.Database;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public User createNewGuest() {
        // Generate guest and add it to database
        User guest = new User();
        //CurrentUser.login = guest.getLogin();
        return database.addNewUser(guest);
    }

    public ChatRoom addUserToChatRoom(User user, String roomName) {
        // check if room exists

        // check if user is already in that room
        if(database.findUserInRoomById(user.getId(), roomName)){
            System.out.println("User '" + user.getNickname() + "' is already in room '" + roomName + "'");
            return null;
        }
        else {
            Optional<ChatRoom> foundRoom = database.findChatRoomByRoomName(roomName);
            ChatRoom room = foundRoom.get();
            database.addUserToRoom(user.getId(), room.getId());
            System.out.println("Successfully added '" + user.getNickname() + "' to chat room '" + roomName + "'");
            return room;
        }
    }

    public void removeUserFromChatRoom(User user, String roomName) {
        // check if user is already in default guest room
        if(roomName.equals("Guest room")) {
            System.out.println("Cant leave default room. You are now chatting in \'Guest room'\'");
        }
        else {
            // leave current chat room
            Optional<ChatRoom> foundRoom = database.findChatRoomByRoomName(roomName);
            if (foundRoom.isPresent( )) {
                ChatRoom room = foundRoom.get( );
                database.removeUserFromRoom(user.getId( ), room.getId( ));
                System.out.println("User '" + user.getNickname( ) + "' has left" + " " + roomName);
        
                // and join default guest room
                Optional<ChatRoom> guestRoom = database.findChatRoomByRoomName("Guest room");
                CurrentRoom.setRoom(guestRoom.get( ));
                System.out.println("Now chatting in \'Guest room'\'");
            } else {
                System.out.println("Room " + roomName + " not found");
            }
        }
    }

    public Response changeUserNickname(User user, String nickname) {
        ChangeNicknameValidator validator = new ChangeNicknameValidator();
        List<Error> errors = validator.validate(nickname);
        if(!errors.isEmpty()){
            return new Response(false, errors);
        }
        else {
            database.changeUserNickname(user.getId(), nickname);
            return new Response(true, null);
        }
    }
}

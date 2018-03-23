package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.domain.ChatRoom;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    
    private final Database database;
    private final ChangeNicknameValidator validator;
    
    public UserService( Database database, ChangeNicknameValidator validator ) {
        this.database = database;
        this.validator = validator;
    }
    
    public User createNewGuest( String userName ) {
        // validate here
        return database.addNewGuest(userName);
    }
    
    public ChatRoom addUserToChatRoom( User user, String roomName ) {
        // check if user is already in that room
        if (database.findUserInRoomById(user.getId( ), roomName)) {
            System.out.println("User '" + user.getNickname( ) + "' is already in room '" + roomName + "'");
            return null;
        } else {
            Optional<ChatRoom> foundRoom = database.findChatRoomByRoomName(roomName);
            if(foundRoom.isPresent()) {
                ChatRoom room = foundRoom.get( );
                database.addUserToRoom(user.getId( ), room.getId( ));
                System.out.println("Successfully added '" + user.getNickname( ) + "' to chat room '" + roomName + "'");
                return room;
            }
            else {
                System.out.println("Room " + roomName + " not found.");
                return null;
            }
        }
    }
    
    public void removeUserFromChatRoom( User user, String roomName ) {
        // check if user is already in default guest room
        if (roomName.equals("Guest room")) {
            System.out.println("Cant leave default room. You are now chatting in \'Guest room'\'");
        } else {
            // leave current chat room
            Optional<ChatRoom> foundRoom = database.findChatRoomByRoomName(roomName);
            if (foundRoom.isPresent( )) {
                ChatRoom room = foundRoom.get( );
                database.removeUserFromRoom(user.getId( ), room.getId( ));
                System.out.println("User '" + user.getNickname( ) + "' has left" + " " + roomName);
                
                // and join default guest room
                Optional<ChatRoom> guestRoom = database.findChatRoomByRoomName("Guest room");
                System.out.println("Now chatting in \'Guest room'\'");
            } else {
                System.out.println("Room " + roomName + " not found");
            }
        }
    }
    
    public Response changeUserNickname( String oldNickname, String newNickname ) {
        List<Error> errors = validator.validate(newNickname);
        if (!errors.isEmpty( )) {
            return new Response(false, errors);
        } else {
            database.changeUserNickname(oldNickname, newNickname);
            return new Response(true, null);
        }
    }
    
    public void setUserInput(User user, String input) {
        user.setInputString(input);
    }
    
    public String getUserInput(User user) {
        return user.getInputString();
    }
    
    public User login(String nickname) {
        Optional<User> user = database.getUserByNickname(nickname);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            // create new
            return createNewGuest(nickname);
        }
    }
}

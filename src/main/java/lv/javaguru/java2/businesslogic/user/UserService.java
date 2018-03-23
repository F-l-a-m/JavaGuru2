/*
package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.room.ActiveRoom;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    
    @Autowired private Database database;
    @Autowired private ChangeNicknameValidator validator;
    
    public User createNewGuest( String userName ) {
        // validate here
        return database.addNewGuest(userName);
    }
    
    public void addUserToChatRoom( User user, String roomName ) {
        // check if user is already in that room
        if (database.findUserInRoomById(user.getId( ), roomName)) {
            System.out.println("User '" + user.getNickname( ) + "' is already in room '" + roomName + "'");
        } else {
            Optional<Room> foundRoom = database.findChatRoomByRoomName(roomName);
            if(foundRoom.isPresent()) {
                Room room = foundRoom.get( );
                database.addUserToRoom(user.getId( ), room.getId( ));
                System.out.println("Successfully added '" + user.getNickname( ) + "' to message room '" + roomName + "'");
            }
            else {
                System.out.println("Room " + roomName + " not found.");
            }
        }
    }
    
    public void removeUserFromChatRoom( User user, ActiveRoom activeRoom ) {
        // check if user is already in default guest room
        String roomName = activeRoom.getRoom().getName();
        if (roomName.equals("Guest room")) {
            System.out.println("Cant leave default room. You are now chatting in 'Guest room'");
        } else {
            // leave current message room
            Optional<Room> foundRoom = database.findChatRoomByRoomName(roomName);
            if (foundRoom.isPresent( )) {
                Room room = foundRoom.get( );
                database.removeUserFromRoom(user.getId( ), room.getId( ));
                System.out.println("User '" + user.getNickname( ) + "' has left" + " " + roomName);
                // and join default guest room
                Optional<Room> guestRoom = database.findChatRoomByRoomName("Guest room");
                if(guestRoom.isPresent()) {
                    activeRoom.setRoom(guestRoom.get());
                }
                else {
                    System.out.println("Error, guest room not found in DB!");
                }
                System.out.println("Now chatting in 'Guest room'");
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
    
    public User logIn( String nickname) {
        Optional<User> user = database.getUserByNickname(nickname);
        if(user.isPresent()) {
            database.updateUserActiveStatus(user.get(), true);
            return user.get();
        }
        else {
            // create new
            return createNewGuest(nickname);
        }
    }
    
    public void logOut(User user) {
        database.updateUserActiveStatus(user, false);
    }
}
*/

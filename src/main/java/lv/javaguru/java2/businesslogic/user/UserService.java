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
    private ChangeNicknameValidator validator;

    public UserService(Database database, ChangeNicknameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public User initializeNewGuest() {
        User guest = null;
        boolean success = false;
        // generate random guest and check if record exists in db
        while (!success) {
            int rand = (int) (Math.random( ) * 100);
            String nickname = "guest" + Integer.toString(rand);
            Optional<User> search = database.getUserByNickname(nickname);
            if (search.isPresent( )) {
                User foundUser = search.get();
                // check if guest account is active
                if (foundUser.isActive( )) {
                    System.out.println("User " + nickname + " is already active");
                    continue;
                }
                else {
                    // set that account active and use it
                    database.updateUserActiveStatus(foundUser, true);
                    guest = foundUser;
                    success = true;
                    System.out.println("Activating account " + nickname);
                }
            }
            else {
                guest = new User(nickname);
                guest.setActive(true);
                database.addNewUser(guest);
                success = true;
                System.out.println("Created new user " + nickname);
            }
        }
        System.out.println("Hello " + guest.getNickname());
        return guest;
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
        List<Error> errors = validator.validate(nickname);
        if(!errors.isEmpty()) {
            return new Response(false, errors);
        }
        else {
            database.changeUserNickname(user.getId(), nickname);
            return new Response(true, null);
        }
    }
}

package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.database.Database;

import java.util.List;
import java.util.Optional;

public class UserService {

    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    /*public void setCurrentUser(User user) {
        CurrentUser.id = user.getId();
        CurrentUser.login = user.getLogin();
        CurrentUser.nickname = user.getNickname();
        CurrentUser.password = user.getPassword();
    }

    public User getCurrentUser() {
        User u = new User();
        u.setId(CurrentUser.id);
        u.setLogin(CurrentUser.login);
        u.setNickname(getCurrentUser().getNickname());
        u.setNickname(getCurrentUser().getPassword());
        return u;
    }*/

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
        Optional<ChatRoom> foundRoom = database.findChatRoomByRoomName(roomName);
        if(foundRoom.isPresent()) {
            ChatRoom room = foundRoom.get();
            database.removeUserFromRoom(user.getId(), room.getId());
            System.out.println("User '" + user.getNickname() + "' has left" + " " + roomName);
        }
        else {
            System.out.println("Room " +roomName + " not found");
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

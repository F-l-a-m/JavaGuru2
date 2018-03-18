package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.database.Database;

import java.util.Optional;

public class UserService {

    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public void setCurrentUser(User user){
        CurrentUser.id = user.getId();
        CurrentUser.login = user.getLogin();
        CurrentUser.nickname = user.getNickname();
        CurrentUser.password = user.getPassword();
    }

    public User getCurrentUser(){
        User u = new User();
        u.setId(CurrentUser.id);
        u.setLogin(CurrentUser.login);
        u.setNickname(getCurrentUser().getNickname());
        u.setNickname(getCurrentUser().getPassword());
        return u;
    }

    public void createNewUser(){
        // Generate guest and add it to database
        User guest = new User();
        CurrentUser.login = guest.getLogin();
        database.addNewUser(guest);
    }

    public void addUserToChatRoom(User user, String roomName) {
        // check if user is already in that room
        if(database.findUserInARoom(user.getId(), roomName)){
            System.out.println("User '" + user.getNickname() + "' is already in room '" + roomName + "'");
        }
        else {
            Optional<ChatRoom> foundRoom;
            foundRoom = database.findChatRoom(roomName);
            ChatRoom r = foundRoom.get();
            database.addUserToRoom(user.getId(), r.getId());
            System.out.println("Successfully added '" + user.getNickname() + "' to chat room '" + roomName + "'");
        }

    }

    public void removeUserFromChatRoom(User user, String roomName) {
        Optional<ChatRoom> foundRoom = database.findChatRoom(roomName);
        if(foundRoom.isPresent()) {
            ChatRoom room = foundRoom.get();
            database.removeUserFromRoom(user.getId(), room.getId());
            System.out.println("User '" + user.getNickname() + "' has left" + " " + roomName);
        }
        else {
            System.out.println("Room " +roomName + " not found");
        }

    }
}

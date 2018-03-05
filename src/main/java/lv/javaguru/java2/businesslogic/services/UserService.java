package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.businesslogic.models.User;
import lv.javaguru.java2.database.Database;

public class UserService {

    private Database database;
    private User newUser;

    public UserService(Database database) {
        this.database = database;
    }

    public User getCurrentUser(){
        return database.getCurrentUser();
    }

    public void setCurrentUser(User user){
        database.setCurrentUser(user);
    }

    public void createNewUser(){
        this.newUser = new User();
        database.setCurrentUser(newUser);
    }

    public void changeUserNickname(String nickname){
        // validate here
        User currentUser = getCurrentUser();
        currentUser.setNickname(nickname);
        database.setCurrentUser(currentUser);
    }
}

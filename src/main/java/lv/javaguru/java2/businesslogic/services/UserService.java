package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.businesslogic.models.User;
import lv.javaguru.java2.database.Database;

public class UserService {

    private Database database;
    private User user;

    public UserService(Database database) {
        this.database = database;
    }

    public User getCurrentUser(){
        return database.getCurrentUser();
    }

    public void createNewUser(){
        this.user = new User();
        database.createNewUser(user);
    }

    public void ChangeUserNickname(String nickname){
        // validate here
        User currentUser = getCurrentUser();
        currentUser.setNickname(nickname);
        database.changeUserNickname(user);
    }

}

package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameError;
import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameResponse;
import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameValidator;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class UserService {

    private Database database;
    private User newUser;
    private ChangeNicknameValidator changeNicknameValidator;

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
}

package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;

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
        // Generete guest and add it to database
        User guest = new User();
        database.addNewUser(guest);
    }
}

package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.businesslogic.models.User;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class UserService {

    private Database database;
    private User newUser;
    private ValidateNickname validateNickname;

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

    public Response changeUserNickname(String nickname){
        // validate here
        ValidateNickname validateNickname = new ValidateNickname(nickname);
        List<ValidationError> errors = validateNickname.validate();
        if(!errors.isEmpty()){
            return new Response(false, errors);
        }

        User currentUser = getCurrentUser();
        currentUser.setNickname(nickname);
        database.setCurrentUser(currentUser);

        return new Response(true, null);
    }
}

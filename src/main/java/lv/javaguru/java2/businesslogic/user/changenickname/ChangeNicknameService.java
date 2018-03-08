package lv.javaguru.java2.businesslogic.user.changenickname;

import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class ChangeNicknameService {

    private Database database;
    private User newUser;
    private ChangeNicknameValidator changeNicknameValidator;

    public ChangeNicknameService(Database database) {
        this.database = database;
    }

    public ChangeNicknameResponse changeUserNickname(String nickname){
        ChangeNicknameValidator changeNicknameValidator = new ChangeNicknameValidator(nickname);
        List<ChangeNicknameError> errors = changeNicknameValidator.validate();
        if(!errors.isEmpty()){
            return new ChangeNicknameResponse(false, errors);
        }

        User currentUser = database.getCurrentUser();
        currentUser.setNickname(nickname);
        database.setCurrentUser(currentUser);

        return new ChangeNicknameResponse(true, null);
    }
}

package lv.javaguru.java2.businesslogic.user.changenickname;

import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

import java.util.List;

public class ChangeNicknameService {

    private Database database;
    private ChangeNicknameValidator changeNicknameValidator;

    public ChangeNicknameService(Database database, ChangeNicknameValidator changeNicknameValidator) {
        this.database = database;
        this.changeNicknameValidator = changeNicknameValidator;
    }

    public ChangeNicknameResponse changeUserNickname(String nickname) {

        List<ChangeNicknameError> errors = changeNicknameValidator.validate(nickname);
        if(!errors.isEmpty()){
            return new ChangeNicknameResponse(false, errors);
        }

        User currentUser = database.getCurrentUser();
        currentUser.setNickname(nickname);
        database.setCurrentUser(currentUser);
        return new ChangeNicknameResponse(true, null);
    }
}

/*
package lv.javaguru.java2.businesslogic.user.changenickname;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

import java.util.List;
import java.util.Optional;

public class ChangeNicknameService {

    private Database database;
    private ChangeNicknameValidator changeNicknameValidator;

    public ChangeNicknameService(Database database, ChangeNicknameValidator changeNicknameValidator) {
        this.database = database;
        this.changeNicknameValidator = changeNicknameValidator;
    }

    public Response changeUserNickname(String nickname) {

        List<Error> errors = changeNicknameValidator.validate(nickname);
        if(!errors.isEmpty()){
            return new Response(false, errors);
        }

        Optional<User> lastUser = database.getLastUser();
        if(lastUser.isPresent()){
            ...
        }
        User currentUser = database.getLastUser();
        currentUser.setNickname(nickname);
        database.addNewUser(currentUser);
        return new Response(true, null);
    }
}
*/

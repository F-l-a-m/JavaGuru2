package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindUserService {

    @Autowired private Database database;
    @Autowired private NicknameValidator validator;

    public FindUserResponse findUserByNickname(String nickname) {
        List<Error> errors = validator.validate(nickname);
        Optional<User> search = database.getUserByNickname(nickname);
        if( search.isPresent() ) {
            return new FindUserResponse(search.get(), null, true);
        } else {
            errors.add(new Error("User with " + nickname + " not found"));
            return new FindUserResponse(null, errors, false);
        }
    }
}

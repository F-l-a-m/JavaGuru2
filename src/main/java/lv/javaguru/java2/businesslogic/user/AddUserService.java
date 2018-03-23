package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserService {

    @Autowired private Database database;

    public AddUserResponse addUser(String nickname) {
        User user = database.addNewGuest(nickname);
        return new AddUserResponse(true, user, null);
    }
}

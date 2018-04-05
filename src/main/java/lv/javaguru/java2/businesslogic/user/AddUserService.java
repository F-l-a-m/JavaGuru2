package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AddUserService {
    
    @Autowired private Database database;
    
    // Give only valid nickname
    @Transactional
    public AddUserResponse addUser( String nickname ) {
        User user = database.user_add( nickname );
        return new AddUserResponse( user, true );
    }
}

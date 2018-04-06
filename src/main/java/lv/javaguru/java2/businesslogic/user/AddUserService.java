package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class AddUserService {
    
    @Autowired private Database database;
    @Autowired private NicknameValidator validator;
    
    @Transactional
    public AddUserResponse addUser( String nickname ) {
        
        List<Error> errors = validator.validate( nickname );
        if ( errors.isEmpty( ) ) {
            // Search
            Optional<User> optionalUser = database.user_get( nickname );
            if ( optionalUser.isPresent( ) ) {
                return new AddUserResponse( optionalUser.get( ), null, true );
            } else {
                // Create new
                User user = database.user_add( nickname );
                return new AddUserResponse( user, null, true );
            }
        } else {
            return new AddUserResponse( null, errors, false );
        }
    }
}

package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class User_ChangeNicknameService {
    
    @Autowired private UserDAO userDAO;
    @Autowired private User_NicknameValidator validator;
    
    @Transactional
    public User_ChangeNicknameResponse changeNickname( User user, String newNickname ) {
        List<Error> errors = validator.validate( newNickname );
        if ( !errors.isEmpty( ) ) {
            // Failed, return new nickname validation errors
            return new User_ChangeNicknameResponse(  errors, false );
        } else {
            Optional<User> optionalUser = userDAO.get( newNickname );
            if ( optionalUser.isPresent( ) ) {
                errors.add( new Error( "Failed to change nickname, user with nickname " +
                        newNickname + " already exists" ) );
                return new User_ChangeNicknameResponse( errors, false );
            } else {
                // Change nickname
                userDAO.changeNickname( user, newNickname );
                return new User_ChangeNicknameResponse( null, true );
            }
        }
    }
}

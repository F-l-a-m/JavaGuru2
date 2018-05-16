package lv.javaguru.java2.console.businesslogic.user;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.businesslogic.user.commonValidators.User_NicknameValidator;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class User_ChangeNicknameService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private User_NicknameValidator validator;
    
    @Transactional
    public User_ChangeNicknameResponse changeNickname( String OldNickname, String newNickname ) {
        List<Error> errors = validator.validate( newNickname );
        if ( !errors.isEmpty( ) ) {
            // Failed, return new nickname validation errors
            return new User_ChangeNicknameResponse( null, errors, false );
        } else {
            Optional<User> optionalUser = userRepository.getByNickname( newNickname );
            if ( optionalUser.isPresent( ) ) {
                errors.add( new Error( "Failed to change nickname, user with nickname " +
                        newNickname + " already exists" ) );
                return new User_ChangeNicknameResponse( null, errors, false );
            } else {
                // Change nickname
                optionalUser = userRepository.getByNickname( OldNickname );
                if ( optionalUser.isPresent( ) ) {
                    User user = optionalUser.get( );
                    user.setNickname( newNickname );
                    return new User_ChangeNicknameResponse( user, null, true );
                } else {
                    errors.add( new Error( "Error. User with nickname " +
                            OldNickname + " not found." ) );
                    return new User_ChangeNicknameResponse( null, errors, false );
                }
            }
        }
    }
}

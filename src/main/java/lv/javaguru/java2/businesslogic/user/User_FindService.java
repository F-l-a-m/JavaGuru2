package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class User_FindService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private User_NicknameValidator validator;
    
    @Transactional
    public User_FindResponse find( String nickname ) {
        List<Error> errors = validator.validate( nickname );
        if ( errors.isEmpty( ) ) {
            Optional<User> optionalUser = userRepository.get( nickname );
            if ( optionalUser.isPresent( ) ) {
                return new User_FindResponse( optionalUser.get( ), null, true );
            } else {
                errors.add( new Error( "User with nickname " + nickname + " not found" ) );
                return new User_FindResponse( null, errors, false );
            }
        } else {
            return new User_FindResponse( null, errors, false );
        }
    }
}

package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.domain.builders.UserBuilder.createUser;

@Component
public class User_AddService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private User_NicknameValidator validator;
    
    @Transactional
    public User_AddResponse addUser( String nickname ) {
        
        List<Error> errors = validator.validate( nickname );
        if ( errors.isEmpty( ) ) {
            // Search
            Optional<User> optionalUser = userRepository.getByNickname( nickname );
            if ( optionalUser.isPresent( ) ) {
                return new User_AddResponse( optionalUser.get( ), null, true );
            } else {
                // Create new
                User user = createUser( )
                        .withNickname( nickname )
                        .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                        .build( );
                userRepository.save( user );
                return new User_AddResponse( user, null, true );
            }
        } else {
            return new User_AddResponse( null, errors, false );
        }
    }
}

package lv.javaguru.java2.console.businesslogic.user.registration;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lv.javaguru.java2.console.domain.builders.UserBuilder.createUser;

@Component
class User_RegistrationServiceImpl implements User_RegistrationService {
    
    @Autowired private User_RegistrationValidator validator;
    @Autowired private UserRepository userRepository;
    
    @Override
    @Transactional
    public User_RegistrationResponse register( User_RegistrationRequest request ) {
        
        List<Error> errors = validator.validate( request );
        if ( !errors.isEmpty( ) ) {
            return new User_RegistrationResponse( errors );
        }
        
        User user = createUser( )
                .withLogin( request.getLogin( ) )
                .withPassword( request.getPassword( ) )
                .withNickname( request.getNickname( ) )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        
        userRepository.save( user );
        return new User_RegistrationResponse( user );
    }
}

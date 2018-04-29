package lv.javaguru.java2.businesslogic.user.login;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.User_LoginValidator;
import lv.javaguru.java2.businesslogic.user.User_PasswordValidator;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class User_LoginServiceImpl implements User_LoginService {
    
    
    @Autowired private UserRepository repository;
    @Autowired private User_LoginServiceValidator validator;
    
    @Override
    @Transactional
    public User_LoginResponse login( User_LoginRequest request ) {
        List<Error> errors = validator.validate( request );
        if ( !errors.isEmpty( ) ) {
            return new User_LoginResponse( errors );
        }
        
        Optional<User> optionalUser = repository.getByLogin( request.getLogin( ) );
        
        if ( !optionalUser.isPresent( ) ) {
            errors.add( new Error( "User with login " + request.getLogin( ) + " not found" ) );
            return new User_LoginResponse( errors );
        } else {
            String truePassword = optionalUser.get( ).getPassword( );
            String enteredPassword = request.getPassword( );
            if ( enteredPassword.equals( truePassword ) ) {
                return new User_LoginResponse( optionalUser.get( ) );
            } else {
                errors.add( new Error( "Password does not match!" ) );
                return new User_LoginResponse( errors );
            }
        }
    }
}

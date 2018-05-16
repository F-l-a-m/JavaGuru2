package lv.javaguru.java2.console.businesslogic.user.commonValidators;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class User_LoginValidator {
    
    @Autowired private UserRepository repository;
    private String login;
    private boolean isEmpty;
    
    public List<Error> validate( String login ) {
        this.login = login;
        isEmpty = false;
        List<Error> errors = new ArrayList<>( );
        validateEmpty( ).ifPresent( errors::add );
        if ( !isEmpty ) validateLength( ).ifPresent( errors::add );
        if ( !isEmpty ) validateAllowedSymbols( ).ifPresent( errors::add );
        if ( !isEmpty ) validateDuplicateLogin( ).ifPresent( errors::add );
        return errors;
    }
    
    private Optional<Error> validateEmpty( ) {
        if ( login == null || login.isEmpty( ) ) {
            isEmpty = true;
            return Optional.of( new Error( "Login must not be empty" ) );
        }
        return Optional.empty( );
    }
    
    private Optional<Error> validateLength( ) {
        if ( login.length( ) > 16 || login.length( ) < 4 )
            return Optional.of( new Error( "Login length should be 4 to 16 symbols" ) );
        return Optional.empty( );
    }
    
    private Optional<Error> validateAllowedSymbols( ) {
        if ( !login.matches( "[0-9A-Za-z]+" ) )
            return Optional.of( new Error( "Login contains illegal characters (letters and numbers only please)" ) );
        return Optional.empty( );
    }
    
    private Optional<Error> validateDuplicateLogin( ) {
        Optional<User> optionalUser = repository.getByLogin( login );
        if ( optionalUser.isPresent( ) ) {
            return Optional.of( new Error( "User with login " + login + " already exists" ) );
        } else {
            return Optional.empty( );
        }
    }
}

package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class User_LoginValidator {
    
    private String login;
    private boolean isEmpty;
    
    public List<Error> validate( String login ) {
        this.login = login;
        isEmpty = false;
        List<Error> errors = new ArrayList<>( );
        validateEmpty( ).ifPresent( errors::add );
        if ( !isEmpty ) validateLength( ).ifPresent( errors::add );
        if ( !isEmpty ) validateAllowedSymbols( ).ifPresent( errors::add );
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
}

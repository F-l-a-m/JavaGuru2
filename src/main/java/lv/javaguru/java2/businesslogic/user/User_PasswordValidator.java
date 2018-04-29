package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class User_PasswordValidator {
    
    private String password;
    private boolean isEmpty;
    
    public List<Error> validate( String password ) {
        this.password = password;
        isEmpty = false;
        List<Error> errors = new ArrayList<>( );
        validateEmpty( ).ifPresent( errors::add );
        if ( !isEmpty ) validateLength( ).ifPresent( errors::add );
        //if ( !isEmpty ) validateAllowedSymbols( ).ifPresent( errors::add );
        return errors;
    }
    
    private Optional<Error> validateEmpty( ) {
        if ( password == null || password.isEmpty( ) ) {
            isEmpty = true;
            return Optional.of( new Error( "Password must not be empty" ) );
        }
        return Optional.empty( );
    }
    
    private Optional<Error> validateLength( ) {
        if ( password.length( ) > 64 || password.length( ) < 8 )
            return Optional.of( new Error( "Password length should be 8 to 64 symbols" ) );
        return Optional.empty( );
    }
    
    /*private Optional<Error> validateAllowedSymbols( ) {
        if ( !password.matches( "[0-9A-Za-z]+" ) )
            return Optional.of( new Error( "Nickname contains illegal characters (letters and numbers only please)" ) );
        return Optional.empty( );
    }*/
}

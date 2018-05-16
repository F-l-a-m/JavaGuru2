package lv.javaguru.java2.console.businesslogic.user;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class User_NicknameValidator {
    
    @Autowired private UserRepository repository;
    private String nickname;
    private boolean isEmpty;
    
    public List<Error> validate( String nickname ) {
        this.nickname = nickname;
        isEmpty = false;
        List<Error> errors = new ArrayList<>( );
        validateEmpty( ).ifPresent( errors::add );
        if ( !isEmpty ) validateLength( ).ifPresent( errors::add );
        if ( !isEmpty ) validateAllowedSymbols( ).ifPresent( errors::add );
        if ( !isEmpty ) validateDuplicateNickname( ).ifPresent( errors::add );
        return errors;
    }
    
    private Optional<Error> validateEmpty( ) {
        if ( nickname == null || nickname.isEmpty( ) ) {
            isEmpty = true;
            return Optional.of( new Error( "Nickname must not be empty" ) );
        }
        return Optional.empty( );
    }
    
    private Optional<Error> validateLength( ) {
        if ( nickname.length( ) > 16 || nickname.length( ) < 2 )
            return Optional.of( new Error( "Nickname length should be 2 to 16 symbols" ) );
        return Optional.empty( );
    }
    
    private Optional<Error> validateAllowedSymbols( ) {
        if ( !nickname.matches( "[0-9A-Za-z]+" ) )
            return Optional.of( new Error( "Nickname contains illegal characters (letters and numbers only please)" ) );
        return Optional.empty( );
    }
    
    private Optional<Error> validateDuplicateNickname( ) {
        Optional<User> optionalUser = repository.getByNickname( nickname );
        if ( optionalUser.isPresent( ) ) {
            return Optional.of( new Error( "User with nickname " + nickname + " already exists" ) );
        } else {
            return Optional.empty( );
        }
    }
}

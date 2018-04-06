package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FindUserService {
    
    @Autowired private Database database;
    @Autowired private NicknameValidator validator;
    
    @Transactional
    public FindUserResponse find( String nickname ) {
        List<Error> errors = validator.validate( nickname );
        if ( errors.isEmpty( ) ) {
            Optional<User> optionalUser = database.user_get( nickname );
            if ( optionalUser.isPresent( ) ) {
                return new FindUserResponse( optionalUser.get( ), null, true );
            } else {
                errors.add( new Error( "User with nickname " + nickname + " not found" ) );
                return new FindUserResponse( null, errors, false );
            }
        } else {
            return new FindUserResponse( null, errors, false );
        }
    }
    
    @Transactional
    public FindUserResponse find( Long userId ) {
        List<Error> errors = new ArrayList<>( );
        Optional<User> optionalUser = database.user_get( userId );
        if ( optionalUser.isPresent( ) ) {
            return new FindUserResponse( optionalUser.get( ), null, true );
        } else {
            errors.add( new Error( "User with id " + userId + " not found" ) );
            return new FindUserResponse( null, errors, false );
        }
    }
}

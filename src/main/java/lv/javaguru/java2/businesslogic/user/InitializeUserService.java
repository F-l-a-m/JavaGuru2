package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializeUserService {
    
    @Autowired private FindUserService findUserService;
    @Autowired private AddUserService addUserService;
    @Autowired private NicknameValidator validator;
    
    public InitializeUserResponse init( String nickname ) {
        
        List<Error> errors = validator.validate( nickname );
        if ( errors.isEmpty( ) ) {
            
            FindUserResponse findUserResponse = findUserService.findUserByNickname( nickname );
            if ( findUserResponse.isSuccess( ) ) {
                // use found user
                return new InitializeUserResponse( findUserResponse.getUser( ), errors, true );
            } else {
                // add new user
                AddUserResponse addUserResponse = addUserService.addUser( nickname );
                if ( addUserResponse.isSuccess( ) ) {
                    return new InitializeUserResponse( addUserResponse.getUser( ), errors, true );
                } else
                    errors.add( new Error( "Failed to add new user" ) );
                    return new InitializeUserResponse( null, errors, false );
            }
        } else
            return new InitializeUserResponse( null, errors, false );
    }
}

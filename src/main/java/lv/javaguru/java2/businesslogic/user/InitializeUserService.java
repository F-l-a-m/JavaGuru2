package lv.javaguru.java2.businesslogic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitializeUserService {
    
    @Autowired private FindUserService findUserService;
    @Autowired private AddUserService addUserService;
    
    public InitializeUserResponse init (String nickname){
        FindUserResponse findUserResponse = findUserService.findUserByNickname( nickname );
        if ( findUserResponse.isSuccess( ) ) {
            // use found user
            return new InitializeUserResponse( findUserResponse.getUser(), null, true);
        } else {
            // add new user
            AddUserResponse addUserResponse = addUserService.addUser( nickname );
            if ( addUserResponse.isSuccess( ) ) {
                return new InitializeUserResponse( addUserResponse.getUser(), null, true);
            } else
                return new InitializeUserResponse( null, addUserResponse.getErrors(), false );
        }
    }
}


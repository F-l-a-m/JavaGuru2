package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class FindUserService {
    
    @Autowired private Database database;
    
    // Give only valid nickname
    @Transactional
    public FindUserResponse findUserByNickname( String nickname ) {
        Optional<User> search = database.findUser( nickname );
        if ( search.isPresent( ) ) {
            return new FindUserResponse( search.get( ), true );
        } else {
            return new FindUserResponse( null, false );
        }
    }
}

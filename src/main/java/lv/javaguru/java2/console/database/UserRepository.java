package lv.javaguru.java2.console.database;

import lv.javaguru.java2.console.domain.User;

import java.util.Optional;

public interface UserRepository {
    
    void save( User user );
    
    Optional<User> getByNickname( String nickname );
    
    Optional<User> getByLogin( String login );
    
    Optional<User> getById( Long id );
    
}

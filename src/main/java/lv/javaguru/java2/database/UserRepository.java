package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

import java.util.Optional;

public interface UserRepository {
    
    void save( User user );
    
    Optional<User> get( String nickname );
    
}

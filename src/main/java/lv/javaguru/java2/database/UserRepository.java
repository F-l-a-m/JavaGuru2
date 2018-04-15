package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

import java.util.Optional;

public interface UserRepository {
    
    void save( User user );
    
    void updateActiveStatus( User user, boolean activeStatus );
    
    Optional<User> get( Long userId );
    
    Optional<User> get( String nickname );
    
    void changeNickname( String oldNickname, String newNickname );
    
    void changeNickname( User user, String newNickname );
    
}
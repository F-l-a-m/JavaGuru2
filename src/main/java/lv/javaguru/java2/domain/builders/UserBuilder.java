package lv.javaguru.java2.domain.builders;

import lv.javaguru.java2.domain.User;

import java.util.Date;

public class UserBuilder {
    
    private Long id;
    private String login;
    private String password;
    private String nickname;
    private Date creationTime;
    private boolean isActive;
    
    private UserBuilder( ) {
    }
    
    public static UserBuilder createUser( ) {
        return new UserBuilder( );
    }
    
    public User build( ) {
        User user = new User( );
        user.setId( id );
        user.setLogin( login );
        user.setPassword( password );
        user.setNickname( nickname );
        user.setCreationTime( creationTime );
        user.setActive( isActive );
        return user;
    }
    
    public UserBuilder withLogin( String login ) {
        this.login = login;
        return this;
    }
    
    public UserBuilder withPassword( String password ) {
        this.password = password;
        return this;
    }
    
    public UserBuilder withId( Long id ) {
        this.id = id;
        return this;
    }
    
    public UserBuilder withNickname( String nickname ) {
        this.nickname = nickname;
        return this;
    }
    
    public UserBuilder withCreationTime( Date creationTime ) {
        this.creationTime = creationTime;
        return this;
    }
    
    public UserBuilder withStatus( Boolean isActive ) {
        this.isActive = isActive;
        return this;
    }
}

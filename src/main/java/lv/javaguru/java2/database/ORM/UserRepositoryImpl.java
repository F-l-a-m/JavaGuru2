package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class UserRepositoryImpl extends ORMRepository implements UserRepository {
    
    @Override
    public void save( User user ) {
        /*User user = new User( nickname, MyTimestamp.getSQLTimestamp( ) );
        Long id = (Long) session( ).save( user );
        user.setId( id );
        return user;*/
        session( ).save( user );
    }
    
    @Override
    public void updateActiveStatus( User user, boolean activeStatus ) {
        user.setActive( activeStatus );
        session( ).update( user );
    }
    
    @Override
    public Optional<User> get( Long userId ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "id", userId ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }
    
    @Override
    public Optional<User> get( String nickname ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "nickname", nickname ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }
    
    @Override
    public void changeNickname( String oldNickname, String newNickname ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "nickname", oldNickname ) )
                .uniqueResult( );
        user.setNickname( newNickname );
        session( ).update( user );
    }
    
    @Override
    public void changeNickname( User user, String newNickname ) {
        user.setNickname( newNickname );
        session( ).update( user );
    }
}

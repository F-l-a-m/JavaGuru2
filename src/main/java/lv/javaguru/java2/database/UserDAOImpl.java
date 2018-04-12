package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {
    
    @Autowired private SessionFactory sessionFactory;
    
    public Session session( ) {
        return sessionFactory.getCurrentSession( );
    }
    
    @Override
    public User add( String nickname ) {
        User user = new User( nickname, MyTimestamp.getSQLTimestamp( ) );
        Long id = (Long) session( ).save( user );
        user.setId( id );
        return user;
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

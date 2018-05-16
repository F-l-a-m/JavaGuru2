package lv.javaguru.java2.console.database.ORM;

import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class UserRepositoryImpl extends ORMRepository implements UserRepository {
    
    @Override
    public void save( User user ) {
        session( ).save( user );
    }
    
    @Override
    public Optional<User> getByNickname( String nickname ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "nickname", nickname ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }

    @Override
    public Optional<User> getByLogin( String login ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "login", login ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }

    @Override
    public Optional<User> getById( Long id ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "id", id ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }
}

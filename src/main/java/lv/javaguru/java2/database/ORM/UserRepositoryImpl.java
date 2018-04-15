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
        session( ).save( user );
    }
    
    @Override
    public Optional<User> get( String nickname ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "nickname", nickname ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }
}

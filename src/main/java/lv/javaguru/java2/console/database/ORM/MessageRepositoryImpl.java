package lv.javaguru.java2.console.database.ORM;

import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.database.MessageRepository;
import lv.javaguru.java2.console.domain.Message;
import lv.javaguru.java2.console.domain.Room;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class MessageRepositoryImpl extends ORMRepository implements MessageRepository {
    
    @Override
    public void save( Message message ) {
        session( ).save( message );
    }
    
    @Override
    public List getAllMessages( Room room) {
        return session( ).createCriteria( Message.class )
                .add( Restrictions.eq( "room", room ) )
                .list( );
    }
}

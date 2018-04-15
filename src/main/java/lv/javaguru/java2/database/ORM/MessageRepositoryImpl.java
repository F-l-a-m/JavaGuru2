package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.MessageRepository;
import lv.javaguru.java2.domain.Message;
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
    public List getAllMessages( Long roomId ) {
        return session( ).createCriteria( Message.class )
                .add( Restrictions.eq( "room_id", roomId ) )
                .list( );
    }
}

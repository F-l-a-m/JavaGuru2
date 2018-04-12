package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.domain.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class MessageDAOImpl implements MessageDAO {
    
    @Autowired private SessionFactory sessionFactory;
    
    public Session session( ) {
        return sessionFactory.getCurrentSession( );
    }
    
    @Override
    public Message add( String messageBody, String nickname, Long roomId ) {
        Message message = new Message( MyTimestamp.getSQLTimestamp( ), nickname, messageBody, roomId );
        Long id = (Long) session( ).save( message );
        message.setId( id );
        return message;
    }
    
    @Override
    public List getAllMessages( Long roomId ) {
        return session( ).createCriteria( Message.class )
                .add( Restrictions.eq( "room_id", roomId ) )
                .list( );
    }
}

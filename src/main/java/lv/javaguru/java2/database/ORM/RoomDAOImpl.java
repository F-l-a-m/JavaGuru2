package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class RoomDAOImpl implements RoomDAO {
    
    @Autowired private SessionFactory sessionFactory;
    
    private Session session( ) {
        return sessionFactory.getCurrentSession( );
    }
    
    @Override
    public Room add( String roomName, String creatorNickname ) {
        Room room = new Room( roomName, creatorNickname, MyTimestamp.getSQLTimestamp( ) );
        Long id = (Long) session().save( room );
        room.setId( id );
        return room;
    }
    
    @Override
    public Optional<Room> get( Long roomId ) {
        Room room = (Room) session().createCriteria( Room.class )
                .add( Restrictions.eq( "id", roomId ) )
                .uniqueResult( );
        return Optional.ofNullable( room );
    }
    
    @Override
    public Optional<Room> get( String roomName ) {
        Room room = (Room) session().createCriteria( Room.class )
                .add( Restrictions.eq( "name", roomName ) )
                .uniqueResult( );
        return Optional.ofNullable( room );
    }
    
    @Override
    public List getAllRooms( ) {
        return session().createCriteria( Room.class )
                .list( );
    }
}

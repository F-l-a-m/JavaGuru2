package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.domain.Room;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class RoomRepositoryImpl extends ORMRepository implements RoomRepository {
    
    @Override
    public void save( Room room ) {
        session( ).save( room );
    }
    
    @Override
    public Optional<Room> get( Long roomId ) {
        Room room = (Room) session( ).createCriteria( Room.class )
                .add( Restrictions.eq( "id", roomId ) )
                .uniqueResult( );
        return Optional.ofNullable( room );
    }
    
    @Override
    public Optional<Room> get( String roomName ) {
        Room room = (Room) session( ).createCriteria( Room.class )
                .add( Restrictions.eq( "name", roomName ) )
                .uniqueResult( );
        return Optional.ofNullable( room );
    }
    
    @Override
    public List getAllRooms( ) {
        return session( ).createCriteria( Room.class )
                .list( );
    }
}

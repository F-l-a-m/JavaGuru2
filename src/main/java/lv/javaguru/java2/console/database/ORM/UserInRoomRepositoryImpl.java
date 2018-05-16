package lv.javaguru.java2.console.database.ORM;

import lv.javaguru.java2.console.database.UserInRoomRepository;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import lv.javaguru.java2.console.domain.UserInRoom;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static lv.javaguru.java2.console.domain.builders.UserInRoomBuilder.createUserInRoom;

@Component
class UserInRoomRepositoryImpl extends ORMRepository implements UserInRoomRepository {
    
    @Override
    public void addUserToRoom( User user, Room room ) {
        UserInRoom userInRoom = createUserInRoom( )
                .withUser( user )
                .withRoom( room ).build( );
        session( ).save( userInRoom );
    }
    
    @Override
    public boolean findUserInRoom( User user, Room room ) {
        String query = "from UserInRoom u " +
                "where u.user = :user " +
                "and u.room = :room";
        UserInRoom userInRoom = (UserInRoom) session( ).createQuery( query )
                .setParameter( "user", user )
                .setParameter( "room", room )
                .uniqueResult( );
        
        /*UserInRoom userInRoom = (UserInRoom) session( ).createCriteria( UserInRoom.class )
                .add( Restrictions.eq( "user_id", userId ) )
                .add( Restrictions.eq( "room_id", roomId ) )
                .uniqueResult( );*/
        if ( userInRoom == null )
            return false;
        else return true;
    }
    
    @Override
    public void removeUserFromRoom( User user, Room room ) {
        String query = "from UserInRoom u " +
                "where u.user = :user " +
                "and u.room = :room";
        UserInRoom userInRoom = (UserInRoom) session( ).createQuery( query )
                .setParameter( "user", user )
                .setParameter( "room", room )
                .uniqueResult( );
        session( ).delete( userInRoom );
    }
    
    @Override
    public List<Room> getAListOfJoinedRooms( User user ) {
        /*
        String query = "from UserInRoom u " +
                "where u.user = :user";
        List<UserInRoom> userInRoomList = session( ).createQuery( query )
                .setParameter( "user", user )
                .list( );
        
        List<Long> roomIds = new ArrayList<>( );
        for ( UserInRoom u : userInRoomList ) {
            roomIds.add( u.getRoom( ).getId( ) );
        }
        
        List<Room> rooms = session( ).createCriteria( Room.class )
                .add( Restrictions.in( "id", roomIds ) )
                .list( );
        
        return rooms;
        */
        
        /*
        Query query = session( ).createQuery( "SELECT u.room FROM UserInRoom u WHERE u.user=:user" );
        query.setParameter( "user", user );
        List<Room> rooms = query.list( );
        return rooms;
        */
        
        return session( ).createQuery( "SELECT u.room FROM UserInRoom u WHERE u.user=:user" )
                .setParameter( "user", user )
                .list( );
    }
}

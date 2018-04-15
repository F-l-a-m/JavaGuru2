package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserInRoom;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class UserInRoomRepositoryImpl extends ORMRepository implements UserInRoomRepository {
    
    @Override
    public void addUserToRoom( UserInRoom userInRoom ) {
        session( ).save( userInRoom );
    }
    
    @Override
    public boolean findUserInRoom( Long userId, Long roomId ) {
        /*User user = (User) session( ).createCriteria( User.class )
                .save( Restrictions.eq( "id", userId) )
                .uniqueResult( );
        return user != null;*/
        UserInRoom userInRoom = (UserInRoom) session( ).createCriteria( UserInRoom.class )
                .add( Restrictions.eq( "user_id", userId ) )
                .add( Restrictions.eq( "room_id", roomId ) )
                .uniqueResult( );
        if ( userInRoom == null )
            return false;
        else return true;
    }
    
    @Override
    public boolean removeUserFromRoom( Long userId, Long roomId ) {
        
        //UserInRoom userInRoom = new UserInRoom( userId, roomId );
        // С таким вариантом
        // org.hibernate.NonUniqueObjectException:
        // A different object with the same identifier value was already associated with the session :
        
        UserInRoom userInRoom = (UserInRoom) session( ).createCriteria( UserInRoom.class )
                .add( Restrictions.eq( "user_id", userId ) )
                .add( Restrictions.eq( "room_id", roomId ) )
                .uniqueResult( );
        if ( userInRoom == null )
            return false;
        session( ).delete( userInRoom );
        return true;
    }
    
    @Override
    public List<Room> getAListOfJoinedRooms( User user ) {
        String query = "from UserInRoom u" +
                "where u.user = :user";
        return session( ).createQuery( query )
                .setParameter( "user", user )
                .list( );
        
        
        
        /*List<UserInRoom> userInRoomList = session( ).createCriteria( UserInRoom.class )
                .save( Restrictions.eq( "user_id", userId ) )
                .list( );
        *//*Query query = session().createQuery("from UserInRoom where user_id = :userId ");
        query.setParameter("user_id", userId);
        List<UserInRoom> userInRoomList = query.list( );*//*
        
        List<Long> roomIds = new ArrayList<>( );
        for ( UserInRoom u : userInRoomList ) {
            roomIds.save( u.getRoom_id( ) );
        }
        
        List<Room> rooms = session( ).createCriteria( Room.class )
                .save( Restrictions.in( "id", roomIds ) )
                .list( );*/
    }
}
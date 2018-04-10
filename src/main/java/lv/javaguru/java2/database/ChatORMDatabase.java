package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserInRoom;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChatORMDatabase implements Database {
    
    @Autowired SessionFactory sessionFactory;
    
    private Session session( ) {
        return sessionFactory.getCurrentSession( );
    }
    
    @Override
    public User user_add( String nickname ) {
        User user = new User( nickname, MyTimestamp.getSQLTimestamp( ) );
        Long id = (Long) session( ).save( user );
        user.setId( id );
        return user;
    }
    
    @Override
    public void user_updateActiveStatus( User user, boolean activeStatus ) {
        user.setActive( activeStatus );
        session( ).update( user );
    }
    
    @Override
    public Optional<User> user_get( Long userId ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "id", userId ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }
    
    @Override
    public Optional<User> user_get( String nickname ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "nickname", nickname ) )
                .uniqueResult( );
        return Optional.ofNullable( user );
    }
    
    @Override
    public void user_changeNickname( String oldNickname, String newNickname ) {
        User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "nickname", oldNickname ) )
                .uniqueResult( );
        user.setNickname( newNickname );
        session( ).update( user );
    }
    
    @Override
    public void user_changeNickname( User user, String newNickname ) {
        user.setNickname( newNickname );
        session( ).update( user );
    }
    
    @Override
    public Room chatRoom_add( String roomName, String creatorNickname ) {
        Room room = new Room( roomName, creatorNickname, MyTimestamp.getSQLTimestamp( ) );
        Long id = (Long) session( ).save( room );
        room.setId( id );
        return room;
    }
    
    @Override
    public Optional<Room> chatRoom_get( Long roomId ) {
        Room room = (Room) session( ).createCriteria( Room.class )
                .add( Restrictions.eq( "id", roomId ) )
                .uniqueResult( );
        return Optional.ofNullable( room );
    }
    
    @Override
    public Optional<Room> chatRoom_get( String roomName ) {
        Room room = (Room) session( ).createCriteria( Room.class )
                .add( Restrictions.eq( "name", roomName ) )
                .uniqueResult( );
        return Optional.ofNullable( room );
    }
    
    @Override
    public List chatRoom_getAllRooms( ) {
        return session( )
                .createCriteria( Room.class )
                .list( );
    }
    
    @Override
    public void userInRoom_addUserToRoom( Long userId, Long roomId ) {
        UserInRoom userInRoom = new UserInRoom( userId, roomId );
        session( ).save( userInRoom );
    }
    
    @Override
    public boolean userInRoom_removeUserFromRoom( Long userId, Long roomId ) {
        
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
    public boolean userInRoom_findUserInRoom( Long userId, Long roomId ) {
        /*User user = (User) session( ).createCriteria( User.class )
                .add( Restrictions.eq( "id", userId) )
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
    public List<Room> userInRoom_getAListOfJoinedRooms( Long userId ) {
        List<UserInRoom> userInRoomList = session( )
                .createCriteria( UserInRoom.class )
                .add( Restrictions.eq( "user_id", userId ) )
                .list( );
        /*Query query = session().createQuery("from UserInRoom where user_id = :userId ");
        query.setParameter("user_id", userId);
        List<UserInRoom> userInRoomList = query.list( );*/
        
        List<Long> roomIds = new ArrayList<>( );
        for ( UserInRoom u : userInRoomList ) {
            roomIds.add( u.getRoom_id( ) );
        }
        
        List<Room> rooms = session( ).createCriteria( Room.class )
                .add( Restrictions.in( "id", roomIds ) )
                .list( );
        
        return rooms;
    }
    
    @Override
    public Message message_add( String messageBody, String nickname, Long roomId ) {
        Message message = new Message( MyTimestamp.getSQLTimestamp( ), nickname, messageBody, roomId );
        Long id = (Long) session( ).save( message );
        message.setId( id );
        return message;
    }
    
    @Override
    public List message_getAllMessages( Long roomId ) {
        return session( )
                .createCriteria( Message.class )
                .add( Restrictions.eq( "room_id", roomId ) )
                .list( );
    }
}

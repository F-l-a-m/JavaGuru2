package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.message.MyTimestamp;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
@SuppressWarnings("SyntaxError")
public class ChatJDBCDatabase extends JDBCDatabase implements Database {
    
    // user management
    @Override
    public User addUser( String nickname ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into user(id, nickname, creationTime, isActive) " +
                    "values(default, ?, ?, false)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1, nickname );
            Timestamp timestamp = MyTimestamp.getSQLTimestamp( );
            preparedStatement.setTimestamp( 2, timestamp );
            preparedStatement.executeUpdate( );
            ResultSet rs = preparedStatement.getGeneratedKeys( );
            User user = null;
            if ( rs.next( ) ) {
                user = new User( );
                user.setId( rs.getLong( 1 ) );
                user.setNickname( nickname );
                user.setCreationTime( timestamp );
            }
            return user;
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.addNewUser()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public void updateUserActiveStatus( User user, boolean activeStatus ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "update user set isActive = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setBoolean( 1, activeStatus );
            preparedStatement.setLong( 2, user.getId( ) );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.updateUserActiveStatus()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<User> findUser( Long userId ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, userId );
            ResultSet resultSet = preparedStatement.executeQuery( );
            User user = null;
            if ( resultSet.next( ) ) {
                user = new User( );
                user.setId( resultSet.getLong( "id" ) );
                user.setLogin( resultSet.getString( "logIn" ) );
                user.setPassword( resultSet.getString( "password" ) );
                user.setNickname( resultSet.getString( "nickname" ) );
                user.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
                user.setActive( resultSet.getBoolean( "is_active" ) );
            }
            return Optional.ofNullable( user );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.findUser()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<User> findUser( String nickname ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from user where nickname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, nickname );
            ResultSet resultSet = preparedStatement.executeQuery( );
            User user = null;
            if ( resultSet.next( ) ) {
                user = new User( );
                user.setId( resultSet.getLong( "id" ) );
                user.setLogin( resultSet.getString( "logIn" ) );
                user.setPassword( resultSet.getString( "password" ) );
                user.setNickname( resultSet.getString( "nickname" ) );
                user.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
                user.setActive( resultSet.getBoolean( "isActive" ) );
            }
            return Optional.ofNullable( user );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.findUser()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public void changeUserNickname( String oldNickname, String newNickname ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "update user set nickname = ? where nickname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, newNickname );
            preparedStatement.setString( 2, oldNickname );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.changeUserNickname()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public void addUserToRoom( Long userId, Long roomId ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into user_in_room(user_id, room_id) values(?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setLong( 1, userId );
            preparedStatement.setLong( 2, roomId );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.addUserToRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public void removeUserFromRoom( Long userId, Long roomId ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "delete from user_in_room where user_id = ? and room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, userId );
            preparedStatement.setLong( 2, roomId );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.removeUserFromRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public boolean findUserInRoom( Long userId, String roomName ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql =
                    "select * " +
                            "from user_in_room " +
                            "join chat_room on user_in_room.room_id = chat_room.id " +
                            "where  user_in_room.user_id = ? and chat_room.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, userId );
            preparedStatement.setString( 2, roomName );
            ResultSet resultSet = preparedStatement.executeQuery( );
            return resultSet.next( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.findUserInRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    // room management
    @Override
    public Optional<Room> createNewChatRoom( String roomName, String creatorNickname ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into chat_room(id, name, creatorNickname, creationTime) values(default, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1, roomName );
            preparedStatement.setString( 2, creatorNickname );
            Timestamp timestamp = MyTimestamp.getSQLTimestamp( );
            preparedStatement.setTimestamp( 3, timestamp );
            preparedStatement.executeUpdate( );
            ResultSet rs = preparedStatement.getGeneratedKeys( );
            Room room = null;
            if ( rs.next( ) ) {
                room = new Room( );
                room.setId( rs.getLong( 1 ) );
                room.setName( roomName );
                room.setCreatorNickname( creatorNickname );
                room.setCreationTime( timestamp );
            }
            return Optional.ofNullable( room );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.createNewChatRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<Room> findChatRoom( Long roomId ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from chat_room where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, roomId );
            ResultSet resultSet = preparedStatement.executeQuery( );
            Room room = null;
            if ( resultSet.next( ) ) {
                room = new Room( );
                room.setId( resultSet.getLong( "id" ) );
                room.setName( resultSet.getString( "name" ) );
            }
            return Optional.ofNullable( room );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.findChatRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<Room> findChatRoom( String roomName ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from chat_room where name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, roomName );
            ResultSet resultSet = preparedStatement.executeQuery( );
            Room room = null;
            if ( resultSet.next( ) ) {
                room = new Room( );
                room.setId( resultSet.getLong( "id" ) );
                room.setName( resultSet.getString( "name" ) );
                room.setCreatorNickname( resultSet.getString( "creatorNickname" ) );
                room.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
            }
            return Optional.ofNullable( room );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.findChatRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public List<Room> getListOfAllRooms( ) {
        Connection connection = null;
        List<Room> listOfAllRooms = new ArrayList<>( );
        try {
            connection = getConnection( );
            Statement statement = connection.createStatement( );
            ResultSet resultSet = statement.executeQuery( "select * from chat_room" );
            Room room;
            while ( resultSet.next( ) ) {
                room = new Room( );
                room.setId( resultSet.getLong( "id" ) );
                room.setName( resultSet.getString( "name" ) );
                room.setCreatorNickname( resultSet.getString( "creatorNickname" ) );
                room.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
                listOfAllRooms.add( room );
            }
            return listOfAllRooms;
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.getListOfAllRooms()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    
    // message management
    @Override
    public Message addChatMessage( String message, String nickname, Long roomId ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into message(id, room_id, creationTime, user_nickname, message_body) " +
                    "values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setLong( 1, roomId );
            Timestamp timestamp = MyTimestamp.getSQLTimestamp( );
            preparedStatement.setTimestamp( 2, timestamp );
            preparedStatement.setString( 3, nickname );
            preparedStatement.setString( 4, message );
            preparedStatement.executeUpdate( );
            ResultSet rs = preparedStatement.getGeneratedKeys( );
            Message msg = null;
            if ( rs.next( ) ) {
                msg = new Message( );
                msg.setId( rs.getLong( 1 ) );
                msg.setRoom_id( roomId );
                msg.setCreationTime( timestamp );
                msg.setUser_nickname( nickname );
                msg.setMessage_body( message );
            }
            return msg;
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.addChatMessage()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }

    @Override
    public List<Message> getAllChatHistoryInRoom( Long roomId ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from message where room_id = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setLong( 1, roomId );
            ResultSet resultSet = preparedStatement.executeQuery( );
            List<Message> messages = new ArrayList<>( );
            Message msg;
            while ( resultSet.next( ) ) {
                msg = new Message( );
                msg.setId( resultSet.getLong( "id" ) );
                msg.setRoom_id( resultSet.getLong( "room_id" ) );
                msg.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
                msg.setUser_nickname( resultSet.getString( "user_nickname" ) );
                msg.setMessage_body( resultSet.getString( "message_body" ) );
                messages.add( msg );
            }
            return messages;
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.getAllChatHistoryInRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
}

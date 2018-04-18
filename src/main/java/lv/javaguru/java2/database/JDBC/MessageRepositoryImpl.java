package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.MessageRepository;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl extends JDBCRepository implements MessageRepository {
    
    
    @Override
    public void save( Message message ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into message" +
                    "(id, room_id, creationTime, user_nickname, message_body) " +
                    "values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setLong( 1, message.getRoom( ).getId( ) );
            Timestamp timestamp = MyTimestamp.getSQLTimestamp( );
            preparedStatement.setTimestamp( 2, timestamp );
            preparedStatement.setString( 3, message.getUser_nickname( ) );
            preparedStatement.setString( 4, message.getMessage_body( ) );
            preparedStatement.executeUpdate( );
            ResultSet rs = preparedStatement.getGeneratedKeys( );
            if ( rs.next( ) ) {
                message.setId( rs.getLong( 1 ) );
            }
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.message_add()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public List getAllMessages( Room room ) {
        Connection connection = null;
        List<Message> messageList = new ArrayList<>( );
        try {
            connection = getConnection( );
            String sql = "select * " +
                    "from message " +
                    "join chat_room on message.room_id = chat_room.id " +
                    "where room_id = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setLong( 1, room.getId( ) );
            ResultSet resultSet = preparedStatement.executeQuery( );
            while ( resultSet.next( ) ) {
                Message message = new Message( );
                message.setId( resultSet.getLong( "id" ) );
                
                Room r = new Room( );
                r.setId( resultSet.getLong( "chat_room.id" ) );
                r.setName( resultSet.getString( "chat_room.name" ) );
                r.setCreatorNickname( resultSet.getString( "chat_room.creatorNickname" ) );
                r.setCreationTime( resultSet.getTimestamp( "chat_room.creationTime" ) );
                message.setRoom( r );
                
                message.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
                message.setUser_nickname( resultSet.getString( "user_nickname" ) );
                message.setMessage_body( resultSet.getString( "message_body" ) );
                messageList.add( message );
            }
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.message_getAllMessages()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
        return messageList;
    }
    
    public void deleteMessage( Message message ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "delete from message where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, message.getId( ) );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.deleteMessage()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
}

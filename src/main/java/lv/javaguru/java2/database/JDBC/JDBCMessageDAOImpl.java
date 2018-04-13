package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.MessageDAO;
import lv.javaguru.java2.domain.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCMessageDAOImpl extends JDBCConfig implements MessageDAO {

    @Override
    public Message add( String message, String nickname, Long roomId ) {
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
            System.out.println( "Exception while execute database.message_add()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }

    @Override
    public List getAllMessages( Long roomId ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from message where room_id = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setLong( 1, roomId );
            ResultSet resultSet = preparedStatement.executeQuery( );
            Message msg;
            List<Message> messages = new ArrayList<>( );
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
            System.out.println( "Exception while execute database.message_getAllMessages()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
}

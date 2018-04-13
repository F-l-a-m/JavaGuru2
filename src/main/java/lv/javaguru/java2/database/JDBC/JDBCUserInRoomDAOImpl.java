package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.database.UserInRoomDAO;
import lv.javaguru.java2.domain.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class JDBCUserInRoomDAOImpl extends JDBCConfig implements UserInRoomDAO {
    
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
            System.out.println( "Exception while execute database.userInRoom_addUserToRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public boolean findUserInRoom( Long userId, Long roomId ) {
        /*Connection connection = null;
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
            System.out.println( "Exception while execute database.userInRoom_findUserInRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }*/
        return false;
    }
    
    @Override
    public boolean removeUserFromRoom( Long userId, Long roomId ) {
        boolean success = false;
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "delete from user_in_room where user_id = ? and room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, userId );
            preparedStatement.setLong( 2, roomId );
            preparedStatement.executeUpdate( );
            success = true;
            return success;
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.userInRoom_removeUserFromRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public List<Room> getAListOfJoinedRooms( Long userId ) {
        return null;
    }
}

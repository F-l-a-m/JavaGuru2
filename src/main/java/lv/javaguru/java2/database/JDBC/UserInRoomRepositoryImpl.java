package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserInRoomRepositoryImpl extends JDBCRepository implements UserInRoomRepository {
    
    @Override
    public void addUserToRoom( User user, Room room ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into user_in_room(user_id, room_id) values(?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setLong( 1, user.getId( ) );
            preparedStatement.setLong( 2, room.getId( ) );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.addUserToRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public boolean findUserInRoom( User user, Room room ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * "
                    + "from user_in_room "
                    + "join chat_room on user_in_room.room_id = chat_room.id "
                    + "where  user_in_room.user_id = ? and chat_room.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, user.getId( ) );
            preparedStatement.setString( 2, room.getName( ) );
            ResultSet resultSet = preparedStatement.executeQuery( );
            if ( resultSet.next( ) ) {
                return true;
            }
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.findUserInRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
        return false;
    }
    
    @Override
    public void removeUserFromRoom( User user, Room room ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "delete from user_in_room where user_id = ? and room_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, user.getId( ) );
            preparedStatement.setLong( 2, room.getId( ) );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.removeUserFromRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public List<Room> getAListOfJoinedRooms( User user ) {
        Connection connection = null;
        List<Room> roomList = new ArrayList<>( );
        try {
            connection = getConnection( );
            String sql = "select * "
                    + "from chat_room "
                    + "join user_in_room on user_in_room.room_id = chat_room.id "
                    + "where user_in_room.user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, user.getId( ) );
            ResultSet resultSet = preparedStatement.executeQuery( );
            Room room = null;
            while ( resultSet.next( ) ) {
                room.setId( resultSet.getLong( "id" ) );
                room.setName( resultSet.getString( "name" ) );
                room.setCreationTime( resultSet.getDate( "creationTime" ) );
                room.setCreatorNickname( resultSet.getString( "creatorNickname" ) );
                roomList.add( room );
            }
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.getAListOfJoinedRooms()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
        return null;
    }
}

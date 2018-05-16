package lv.javaguru.java2.console.database.JDBC;

import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.database.RoomRepository;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepositoryImpl extends JDBCRepository implements RoomRepository {
    
    @Override
    public void save( Room room ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into chat_room(id, name, creatorNickname, creationTime) values(default, ?, ?, ?)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1, room.getName( ) );
            preparedStatement.setString( 2, room.getCreatorNickname( ) );
            Timestamp timestamp = MyTimestamp.getSQLTimestamp( );
            preparedStatement.setTimestamp( 3, timestamp );
            preparedStatement.executeUpdate( );
            ResultSet rs = preparedStatement.getGeneratedKeys( );
            if ( rs.next( ) ) {
                room.setId( rs.getLong( 1 ) );
            }
        } catch (Throwable e) {
            System.out.println( "Exception while execute RoomRepository.save()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<Room> get( String roomName ) {
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
            System.out.println( "Exception while execute RoomRepository.getByNickname()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public List<Room> getAllRooms( ) {
        Connection connection = null;
        List<Room> listOfAllRooms = new ArrayList<>( );
        try {
            connection = getConnection( );
            String sql = "select * from chat_room";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            ResultSet resultSet = preparedStatement.executeQuery( );
            while ( resultSet.next( ) ) {
                Room room = new Room( );
                room.setId( resultSet.getLong( "id" ) );
                room.setName( resultSet.getString( "name" ) );
                room.setCreatorNickname( resultSet.getString( "creatorNickname" ) );
                room.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
                listOfAllRooms.add( room );
            }
        } catch (Throwable e) {
            System.out.println( "Exception while execute RoomRepository.getAllRooms()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
        return listOfAllRooms;
    }
    
    public void deleteRoom( Room room ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "delete from chat_room where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, room.getId( ) );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.deleteRoom()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
}

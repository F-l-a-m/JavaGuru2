package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.domain.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCRoomDAOImpl extends JDBCConfig implements RoomDAO {
    
    @Override
    public Room add( String roomName, String creatorNickname ) {
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
            return room;
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.chatRoom_add()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<Room> get( Long roomId ) {
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
            System.out.println( "Exception while execute database.chatRoom_get()" );
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
            System.out.println( "Exception while execute database.chatRoom_get()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public List getAllRooms( ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            Statement statement = connection.createStatement( );
            ResultSet resultSet = statement.executeQuery( "select * from chat_room" );
            Room room;
            List<Room> listOfAllRooms = new ArrayList<>( );
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
            System.out.println( "Exception while execute database.chatRoom_getAllRooms()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
}

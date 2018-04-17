package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Optional;

public class UserRepositoryImpl extends JDBCRepository implements UserRepository {
    
    @Override
    public void save( User user ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "insert into user(id, nickname, creationTime, isActive) " +
                    "values(default, ?, ?, false)";
            PreparedStatement preparedStatement =
                    connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1, user.getNickname( ) );
            Timestamp timestamp = MyTimestamp.getSQLTimestamp( );
            preparedStatement.setTimestamp( 2, timestamp );
            preparedStatement.executeUpdate( );
            ResultSet rs = preparedStatement.getGeneratedKeys( );
            if ( rs.next( ) ) {
                user.setId( rs.getLong( 1 ) );
            }
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.save()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<User> get( String nickname ) {
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
                user.setNickname( resultSet.getString( "nickname" ) );
                user.setActive( resultSet.getBoolean( "isActive" ) );
                user.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
            }
            return Optional.ofNullable( user );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.get()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    public void deleteUser( User user ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "delete from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, user.getId( ) );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.deleteUser()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
}

package lv.javaguru.java2.console.database.JDBC;

import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;

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
    public Optional<User> getByNickname( String nickname ) {
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
                user.setLogin( resultSet.getString( "login" ) );
                user.setPassword( resultSet.getString( "password" ) );
                user.setNickname( resultSet.getString( "nickname" ) );
                user.setActive( resultSet.getBoolean( "isActive" ) );
                user.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
            }
            return Optional.ofNullable( user );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.getByNickname()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }

    @Override
    public Optional<User> getByLogin( String login ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from user where login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, login );
            ResultSet resultSet = preparedStatement.executeQuery( );
            User user = null;
            if ( resultSet.next( ) ) {
                user = new User( );
                user.setId( resultSet.getLong( "id" ) );
                user.setLogin( resultSet.getString( "login" ) );
                user.setPassword( resultSet.getString( "password" ) );
                user.setNickname( resultSet.getString( "nickname" ) );
                user.setActive( resultSet.getBoolean( "isActive" ) );
                user.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
            }
            return Optional.ofNullable( user );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.getByLogin()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }

    @Override
    public Optional<User> getById( Long id ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "select * from user where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setLong( 1, id );
            ResultSet resultSet = preparedStatement.executeQuery( );
            User user = null;
            if ( resultSet.next( ) ) {
                user = new User( );
                user.setId( resultSet.getLong( "id" ) );
                user.setLogin( resultSet.getString( "login" ) );
                user.setPassword( resultSet.getString( "password" ) );
                user.setNickname( resultSet.getString( "nickname" ) );
                user.setActive( resultSet.getBoolean( "isActive" ) );
                user.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
            }
            return Optional.ofNullable( user );
        } catch (Throwable e) {
            System.out.println( "Exception while execute JDBCRepository.getById()" );
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

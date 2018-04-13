package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Optional;

public class JDBCUserDAOImpl extends JDBCConfig implements UserDAO {
    
    @Override
    public User add( String nickname ) {
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
    public void updateActiveStatus( User user, boolean activeStatus ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "update user set isActive = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setBoolean( 1, activeStatus );
            preparedStatement.setLong( 2, user.getId( ) );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.user_updateActiveStatus()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public Optional<User> get( Long userId ) {
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
            System.out.println( "Exception while execute database.user_get()" );
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
                user.setLogin( resultSet.getString( "logIn" ) );
                user.setPassword( resultSet.getString( "password" ) );
                user.setNickname( resultSet.getString( "nickname" ) );
                user.setCreationTime( resultSet.getTimestamp( "creationTime" ) );
                user.setActive( resultSet.getBoolean( "isActive" ) );
            }
            return Optional.ofNullable( user );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.user_get()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public void changeNickname( String oldNickname, String newNickname ) {
        Connection connection = null;
        try {
            connection = getConnection( );
            String sql = "update user set nickname = ? where nickname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, newNickname );
            preparedStatement.setString( 2, oldNickname );
            preparedStatement.executeUpdate( );
        } catch (Throwable e) {
            System.out.println( "Exception while execute database.user_changeNickname()" );
            e.printStackTrace( );
            throw new RuntimeException( e );
        } finally {
            closeConnection( connection );
        }
    }
    
    @Override
    public void changeNickname( User user, String newNickname ) {
    
    }
}

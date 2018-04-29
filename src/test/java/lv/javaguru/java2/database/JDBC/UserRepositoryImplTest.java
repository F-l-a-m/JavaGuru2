package lv.javaguru.java2.database.JDBC;

import lv.javaguru.java2.domain.User;
import org.junit.*;

import java.util.Optional;

import static lv.javaguru.java2.domain.builders.UserBuilder.createUser;
import static org.junit.Assert.*;

public class UserRepositoryImplTest {
    
    private UserRepositoryImpl repository = new UserRepositoryImpl( );
    private User user;
    
    @Before
    public void init( ) {
        user = createUser( ).withNickname( "TestUser" ).build( );
        repository.save( user );
        System.out.println( "User " + user.getNickname( ) + " successfully added to database." );
    }
    
    @Test
    public void shouldAddUser( ) {
        System.out.println( "shouldAddUser" );
        assertNotNull( user.getId( ) );
        System.out.println( "User id is: " + user.getId( ) );
    }
    
    @Test
    public void shouldFindUser( ) {
        System.out.println( "shouldFindUser" );
        Optional<User> optionalUser = repository.getByNickname( "TestUser" );
        if ( optionalUser.isPresent( ) ) {
            System.out.println( "User " + user.getNickname( ) + " has been found in database." );
        }
        assertTrue( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldNotFindUser( ) {
        System.out.println( "shouldNotFindUser" );
        Optional<User> optionalUser = repository.getByNickname( "TestUser2" );
        if ( !optionalUser.isPresent( ) ) {
            System.out.println( "User TestUser2 has not been found in database." );
        }
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @After
    public void cleanDatabase( ) {
        repository.deleteUser( user );
        System.out.println( "User " + user.getNickname( ) + " successfully deleted from database.\n\n" );
    }
}

package lv.javaguru.java2.console.database.ORM;

import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.configs.SpringAppConfig;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static lv.javaguru.java2.console.domain.builders.UserBuilder.createUser;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class UserRepositoryImplTest {
    
    @Autowired private UserRepository userRepository;
    
    @Test
    public void shouldCreateNewUser( ) {
        User user = createUser( )
                .withNickname( "TestUser" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        userRepository.save( user );
        
        assertNotNull( user.getId( ) );
    }
    
    @Test
    public void shouldFindUserByNickname( ) {
        User user = createUser( )
                .withNickname( "TestUser" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        userRepository.save( user );
        
        Optional<User> optionalUser = userRepository.getByNickname( "TestUser" );
        User foundUser = optionalUser.get( );
        
        assertTrue( optionalUser.isPresent( ) );
        assertEquals( optionalUser.get( ).getNickname( ), "TestUser" );
    }
    
    @Test
    public void shouldFailToFindUser( ) {
        Optional<User> optionalUser = userRepository.getByNickname( "TestUser" );
        
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldChangeUserNickname( ) {
        User user = createUser( )
                .withNickname( "TestUser" )
                .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                .build( );
        userRepository.save( user );
        
        user.setNickname( "NewNickname" );
    
        Optional<User> optionalUser = userRepository.getByNickname( "NewNickname" );
        User foundUser = optionalUser.get( );
        
        assertEquals( foundUser.getNickname( ), "NewNickname" );
    }
}

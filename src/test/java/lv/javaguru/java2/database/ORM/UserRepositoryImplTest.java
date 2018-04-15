package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static lv.javaguru.java2.domain.builders.UserBuilder.createUser;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringAppConfig.class })
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
        
        Optional<User> optionalUser = userRepository.get( "TestUser" );
        User result = optionalUser.get( );
        
        assertEquals( result.getNickname( ), "TestUser" );
        assertNotNull( result.getId( ) );
    }
    
    /*@Test
    public void shouldSetUserActiveStatus( ) {
        User user = userRepository.save( "TestUser" );
        
        userRepository.updateActiveStatus( user, true );
        assertTrue( user.isActive( ) );
        
        userRepository.updateActiveStatus( user, false );
        assertFalse( user.isActive( ) );
    }
    
    @Test
    public void shouldReturnFoundUserById( ) {
        User user = userRepository.save( "TestUser" );
        Long id = user.getId( );
        
        Optional<User> optionalUser = userRepository.get( id );
        
        assertTrue( optionalUser.isPresent( ) );
        assertEquals( optionalUser.get( ).getNickname( ), "TestUser" );
    }
    
    @Test
    public void shouldReturnFoundUserByNickname( ) {
        userRepository.save( "TestUser" );
        
        Optional<User> optionalUser = userRepository.get( "TestUser" );
        
        assertTrue( optionalUser.isPresent( ) );
        assertEquals( optionalUser.get( ).getNickname( ), "TestUser" );
    }
    
    @Test
    public void shouldFailToReturnUserById( ) {
        Optional<User> optionalUser = userRepository.get( Long.MAX_VALUE );
        
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldFailToReturnUserByNickname( ) {
        Optional<User> optionalUser = userRepository.get( "TestUser" );
        
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldChangeUserNicknameByString( ) {
        User user = userRepository.save( "TestUser" );
        
        userRepository.changeNickname( "TestUser", "NewNickname" );
        
        assertEquals( user.getNickname( ), "NewNickname" );
    }
    
    @Test
    public void shouldChangeUserNicknameByObject( ) {
        User user = userRepository.save( "TestUser" );
        
        userRepository.changeNickname( user, "NewNickname" );
        
        assertEquals( user.getNickname( ), "NewNickname" );
    }*/
}

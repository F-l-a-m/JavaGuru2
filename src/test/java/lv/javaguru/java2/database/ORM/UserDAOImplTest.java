package lv.javaguru.java2.database.ORM;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
@Transactional
public class UserDAOImplTest {
    
    @Autowired private UserDAO userDAO;
    
    @Test
    public void shouldCreateNewUser( ) {
        User user = userDAO.add( "TestUser" );
        
        assertEquals( user.getNickname( ), "TestUser" );
        assertNotNull( user.getId( ) );
    }
    
    @Test
    public void shouldSetUserActiveStatus( ) {
        User user = userDAO.add( "TestUser" );
        
        userDAO.updateActiveStatus( user, true );
        assertTrue( user.isActive( ) );
        
        userDAO.updateActiveStatus( user, false );
        assertFalse( user.isActive( ) );
    }
    
    @Test
    public void shouldReturnFoundUserById( ) {
        User user = userDAO.add( "TestUser" );
        Long id = user.getId( );
        
        Optional<User> optionalUser = userDAO.get( id );
        
        assertTrue( optionalUser.isPresent( ) );
        assertEquals( optionalUser.get( ).getNickname( ), "TestUser" );
    }
    
    @Test
    public void shouldReturnFoundUserByNickname( ) {
        userDAO.add( "TestUser" );
        
        Optional<User> optionalUser = userDAO.get( "TestUser" );
        
        assertTrue( optionalUser.isPresent( ) );
        assertEquals( optionalUser.get( ).getNickname( ), "TestUser" );
    }
    
    @Test
    public void shouldFailToReturnUserById( ) {
        Optional<User> optionalUser = userDAO.get( Long.MAX_VALUE );
        
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldFailToReturnUserByNickname( ) {
        Optional<User> optionalUser = userDAO.get( "TestUser" );
        
        assertFalse( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldChangeUserNicknameByString( ) {
        User user = userDAO.add( "TestUser" );
        
        userDAO.changeNickname( "TestUser", "NewNickname" );
        
        assertEquals( user.getNickname( ), "NewNickname" );
    }
    
    @Test
    public void shouldChangeUserNicknameByObject( ) {
        User user = userDAO.add( "TestUser" );
        
        userDAO.changeNickname( user, "NewNickname" );
        
        assertEquals( user.getNickname( ), "NewNickname" );
    }
}

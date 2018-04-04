package lv.javaguru.java2.database;

import lv.javaguru.java2.configs.SpringAppConfig;
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
public class ChatORMDatabaseTest {
    
    @Autowired Database database;
    
    @Test
    public void shouldAddUserToDatabase( ) {
        User user = null;
        
        user = database.addUser( "TestUser" );
        
        assertEquals( user.getNickname( ), "TestUser" );
        assertNotNull( user.getId( ) );
    }
    
    @Test
    public void shouldSetUserActiveStatus( ) {
        // add user for test method
        User user = null;
        user = database.addUser( "TestUser" );
        
        database.updateUserActiveStatus( user, true );
        assertEquals( user.isActive( ), true );
        
        database.updateUserActiveStatus( user, false );
        assertEquals( user.isActive( ), false );
    }
    
    @Test
    public void shouldReturnFoundUser( ) {
        User user = null;
        user = database.addUser( "TestUser" );
        Long id = user.getId( );
        
        Optional<User> optionalUser = null;
        optionalUser = database.findUser( id );
        
        assertTrue( optionalUser.isPresent( ) );
        
        optionalUser = null;
        optionalUser = database.findUser( "TestUser" );
        assertTrue( optionalUser.isPresent( ) );
    }
    
    @Test
    public void shouldChangeUserNickname(){
        /*User user = null;
        user = database.addUser( "TestUser" );
        
        database.changeUserNickname( "TestUser", "NewNickname" );*/
    }
}

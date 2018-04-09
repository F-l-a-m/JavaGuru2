package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class User_AddServiceTest {
    
    @Mock private Database database;
    @Mock private User_NicknameValidator validator;
    
    @InjectMocks
    private User_AddService userAddService = new User_AddService( );
    
    @Test
    public void shouldAddNewUserToDatabase( ) {
        String userNickname = "TestUser";
        List<Error> errors = new ArrayList<>( );
        User user = Mockito.mock( User.class );
        Mockito.when( validator.validate( userNickname ) )
                .thenReturn( errors );
        Mockito.when( database.user_get( userNickname ) )
                .thenReturn( Optional.of( user ) );
        
        User_AddResponse userAddResponse = userAddService.addUser( userNickname );
        
        assertTrue( userAddResponse.isSuccess( ) );
        assertNotNull( userAddResponse.getUser( ) );
        assertNull( userAddResponse.getErrors( ) );
    }
    
    @Test
    public void shouldFailToAddNewUserToDatabase( ) {
        List<Error> errors = new ArrayList<>( );
        errors.add( new Error( "Nickname length should be 2 to 16 symbols" ) );
        errors.add( new Error( "Nickname contains illegal characters (letters and numbers only please)" ) );
        Mockito.when( validator.validate( "$" ) )
                .thenReturn( errors );
        
        User_AddResponse userAddResponse1 = userAddService.addUser( "$" );
        
        assertFalse( userAddResponse1.isSuccess( ) );
        assertNull( userAddResponse1.getUser( ) );
        assertEquals( userAddResponse1.getErrors( ), errors );
        
        Mockito.when( validator.validate( "$$" ) )
                .thenReturn( errors );
        errors.clear( );
        errors.add( new Error( "Nickname contains illegal characters (letters and numbers only please)" ) );
        
        User_AddResponse userAddResponse2 = userAddService.addUser( "$$" );
        
        assertFalse( userAddResponse2.isSuccess( ) );
        assertNull( userAddResponse2.getUser( ) );
        assertEquals( userAddResponse2.getErrors( ), errors );
    }
}

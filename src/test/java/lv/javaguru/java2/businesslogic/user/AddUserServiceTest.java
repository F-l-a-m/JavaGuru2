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
public class AddUserServiceTest {
    
    @Mock private Database database;
    @Mock private NicknameValidator validator;
    
    @InjectMocks
    private AddUserService addUserService = new AddUserService( );
    
    @Test
    public void shouldAddNewUserToDatabase( ) {
        String userNickname = "TestUser";
        List<Error> errors = new ArrayList<>( );
        User user = Mockito.mock( User.class );
        Mockito.when( validator.validate( userNickname ) )
                .thenReturn( errors );
        Mockito.when( database.user_get( userNickname ) )
                .thenReturn( Optional.of( user ) );
        
        AddUserResponse addUserResponse = addUserService.addUser( userNickname );
        
        assertTrue( addUserResponse.isSuccess( ) );
        assertNotNull( addUserResponse.getUser( ) );
        assertNull( addUserResponse.getErrors( ) );
    }
    
    @Test
    public void shouldFailToAddNewUserToDatabase( ) {
        List<Error> errors = new ArrayList<>( );
        errors.add( new Error( "Nickname length should be 2 to 16 symbols" ) );
        errors.add( new Error( "Nickname contains illegal characters (letters and numbers only please)" ) );
        Mockito.when( validator.validate( "$" ) )
                .thenReturn( errors );
        
        AddUserResponse addUserResponse1 = addUserService.addUser( "$" );
        
        assertFalse( addUserResponse1.isSuccess( ) );
        assertNull( addUserResponse1.getUser( ) );
        assertEquals( addUserResponse1.getErrors( ), errors );
        
        Mockito.when( validator.validate( "$$" ) )
                .thenReturn( errors );
        errors.clear( );
        errors.add( new Error( "Nickname contains illegal characters (letters and numbers only please)" ) );
        
        AddUserResponse addUserResponse2 = addUserService.addUser( "$$" );
        
        assertFalse( addUserResponse2.isSuccess( ) );
        assertNull( addUserResponse2.getUser( ) );
        assertEquals( addUserResponse2.getErrors( ), errors );
    }
}

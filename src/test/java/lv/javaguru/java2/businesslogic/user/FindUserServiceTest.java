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
public class FindUserServiceTest {
    
    @Mock private Database database;
    @Mock private NicknameValidator validator;
    
    @InjectMocks
    private FindUserService findUserService = new FindUserService( );
    
    @Test
    public void shouldReturnFoundUserByNickname( ) {
        List<Error> errors = new ArrayList<>( );
        String nickname = "TestUser";
        User user = Mockito.mock( User.class );
        Mockito.when( validator.validate( nickname ) )
                .thenReturn( errors );
        Mockito.when( database.user_get( nickname ) )
                .thenReturn( Optional.of( user ) );
        
        FindUserResponse findUserResponse = findUserService.find( nickname );
        
        assertTrue( findUserResponse.isSuccess( ) );
        assertNotNull( findUserResponse.getUser( ) );
        assertNull( findUserResponse.getErrors( ) );
    }
    
    @Test
    public void shouldReturnFoundUserById( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        User user = Mockito.mock( User.class );
        Mockito.when( database.user_get( userId ) )
                .thenReturn( Optional.of( user ) );
        
        FindUserResponse findUserResponse = findUserService.find( userId );
        
        assertTrue( findUserResponse.isSuccess( ) );
        assertNotNull( findUserResponse.getUser( ) );
        assertNull( findUserResponse.getErrors( ) );
    }
    
    @Test
    public void shouldFailToFindUserByNickname( ) {
        List<Error> errors = new ArrayList<>( );
        errors.add( new Error( "Nickname length should be 2 to 16 symbols" ) );
        errors.add( new Error( "Nickname contains illegal characters (letters and numbers only please)" ) );
        Mockito.when( validator.validate( "$" ) )
                .thenReturn( errors );
        
        FindUserResponse findUserResponse = findUserService.find( "$" );
        
        assertFalse( findUserResponse.isSuccess( ) );
        assertNotNull( findUserResponse.getErrors( ) );
        assertNull( findUserResponse.getUser( ) );
        assertEquals( errors.size( ), findUserResponse.getErrors( ).size( ) );
    }
    
    @Test
    public void shouldFailToFindUserById( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        List<Error> errors = new ArrayList<>( );
        errors.add( new Error( "User with id " + userId + " not found" ) );
        Mockito.when( database.user_get( userId ) )
                .thenReturn( Optional.empty( ) );
        
        FindUserResponse findUserResponse = findUserService.find( userId );
        
        assertFalse( findUserResponse.isSuccess( ) );
        assertNotNull( findUserResponse.getErrors( ) );
        assertNull( findUserResponse.getUser( ) );
        assertEquals( errors.size( ), findUserResponse.getErrors( ).size( ) );
    }
}

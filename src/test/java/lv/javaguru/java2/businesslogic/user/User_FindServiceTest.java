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
public class User_FindServiceTest {
    
    @Mock private Database database;
    @Mock private User_NicknameValidator validator;
    
    @InjectMocks
    private User_FindService userFindService = new User_FindService( );
    
    @Test
    public void shouldFindUserByNickname( ) {
        List<Error> errors = new ArrayList<>( );
        String nickname = "TestUser";
        User user = Mockito.mock( User.class );
        Mockito.when( validator.validate( nickname ) )
                .thenReturn( errors );
        Mockito.when( database.user_get( nickname ) )
                .thenReturn( Optional.of( user ) );
        
        User_FindResponse userFindResponse = userFindService.find( nickname );
        
        assertTrue( userFindResponse.isSuccess( ) );
        assertNotNull( userFindResponse.getUser( ) );
        assertNull( userFindResponse.getErrors( ) );
    }
    
    @Test
    public void shouldFindUserById( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        User user = Mockito.mock( User.class );
        Mockito.when( database.user_get( userId ) )
                .thenReturn( Optional.of( user ) );
        
        User_FindResponse userFindResponse = userFindService.find( userId );
        
        assertTrue( userFindResponse.isSuccess( ) );
        assertNotNull( userFindResponse.getUser( ) );
        assertNull( userFindResponse.getErrors( ) );
    }
    
    @Test
    public void shouldFailToFindUserByNickname( ) {
        String nickname = "TestUser";
        List<Error> errors = new ArrayList<>( );
        Mockito.when( validator.validate( nickname ) )
                .thenReturn( errors );
        Mockito.when( database.user_get( nickname ) )
                .thenReturn( Optional.empty( ) );
        
        User_FindResponse userFindResponse = userFindService.find( nickname );
        
        assertFalse( userFindResponse.isSuccess( ) );
        assertNotNull( userFindResponse.getErrors( ) );
        assertNull( userFindResponse.getUser( ) );
    }
    
    @Test
    public void shouldFailToFindUserById( ) {
        Long userId = Integer.toUnsignedLong( 1 );
        List<Error> errors = new ArrayList<>( );
        Mockito.when( database.user_get( userId ) )
                .thenReturn( Optional.empty( ) );
        
        User_FindResponse userFindResponse = userFindService.find( userId );
        
        assertFalse( userFindResponse.isSuccess( ) );
        assertNotNull( userFindResponse.getErrors( ) );
        assertNull( userFindResponse.getUser( ) );
    }
}
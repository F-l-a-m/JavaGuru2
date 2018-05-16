package lv.javaguru.java2.console.businesslogic.room;

import lv.javaguru.java2.console.businesslogic.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class Room_NameValidatorTest {
    
    @InjectMocks
    Room_NameValidator validator = new Room_NameValidator( );
    
    @Test
    public void shouldNotReturnErrors( ) {
        String roomname1 = "ab"; // length of 2 is allowed
        String roomname2 = "TestNickname";
        String roomname3 = "0123456789123456"; // length of 16 is allowed
        
        List<Error> result1 = validator.validate( roomname1 );
        List<Error> result2 = validator.validate( roomname2 );
        List<Error> result3 = validator.validate( roomname3 );
        
        assertTrue( result1.isEmpty( ) );
        assertTrue( result2.isEmpty( ) );
        assertTrue( result3.isEmpty( ) );
    }
    
    @Test
    public void shouldReturnErrorsBecauseOfLength( ) {
        String roomname1 = "a"; // length of 2
        String roomname2 = "01234567891234567"; // length of 17
        
        List<Error> result1 = validator.validate( roomname1 );
        List<Error> result2 = validator.validate( roomname2 );
        
        assertEquals( result1.size( ), 1 );
        assertEquals( result2.size( ), 1 );
    }
    
    @Test
    public void shouldReturnErrorsWhenNicknameIsEmpty( ) {
        String roomname1 = "";
        String roomname2 = " ";
        String roomname3 = "  ";
        String roomname4 = null;
        
        List<Error> result1 = validator.validate( roomname1 );
        List<Error> result2 = validator.validate( roomname2 );
        List<Error> result3 = validator.validate( roomname3 );
        List<Error> result4 = validator.validate( roomname4 );
        
        assertEquals( result1.size( ), 1 );
        assertEquals( result2.size( ), 2 ); // One error for length, and one for restricted symbol
        assertEquals( result3.size( ), 1 );
        assertEquals( result4.size( ), 1 );
    }
    
    @Test
    public void shouldReturnErrorWhenNicknameIsShort( ) {
        String RoomName = "a";
        
        List<Error> result = validator.validate( RoomName );
        
        assertEquals( result.size( ), 1 );
    }
    
    @Test
    public void shouldReturnErrorsWhenNicknameContainsDisallowedSymbols( ) {
        String roomname1 = "$";
        String roomname2 = "!@";
        String roomname3 = "a$";
        String roomname4 = "$a";
        String roomname5 = "abc$%^qwerty";
        
        List<Error> result1 = validator.validate( roomname1 );
        List<Error> result2 = validator.validate( roomname2 );
        List<Error> result3 = validator.validate( roomname3 );
        List<Error> result4 = validator.validate( roomname4 );
        List<Error> result5 = validator.validate( roomname5 );
        
        assertEquals( result1.size( ), 2 ); // One error for length and one for restricted symbol
        assertEquals( result2.size( ), 1 );
        assertEquals( result3.size( ), 1 );
        assertEquals( result4.size( ), 1 );
        assertEquals( result5.size( ), 1 );
    }
    
}
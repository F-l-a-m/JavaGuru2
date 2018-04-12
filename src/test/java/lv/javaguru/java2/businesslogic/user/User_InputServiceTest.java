package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.configs.SpringAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfig.class})
public class User_InputServiceTest implements Constants {
    
    @Autowired private User_InputService userInputService;
    
    @Test
    public void shouldReturnEmptyConstant( ) {
        User_InputResponse response1 = userInputService.handle( "" );
        byte result1 = response1.getCommand( );
        User_InputResponse response2 = userInputService.handle( " " );
        byte result2 = response2.getCommand( );
        User_InputResponse response3 = userInputService.handle( "  " );
        byte result3 = response3.getCommand( );
        
        assertEquals( result1, Constants.EMPTY_MESSAGE );
        assertEquals( result2, Constants.EMPTY_MESSAGE );
        assertEquals( result3, Constants.EMPTY_MESSAGE );
    }
    
    @Test
    public void shouldReturnBadCommandConstant() {
        User_InputResponse response = userInputService.handle("/");
        byte result = response.getCommand();
        assertEquals(result, Constants.BAD_COMMAND);
    }
    
    @Test
    public void shouldReturnRefreshConstant() {
        User_InputResponse response = userInputService.handle("/r");
        byte result = response.getCommand();
        assertEquals(result, Constants.GET_CHAT_HISTORY);
    }
    
    @Test
    public void shouldReturnPrintMessageConstant() {
        User_InputResponse response = userInputService.handle("Hello");
        byte result = response.getCommand();
        assertEquals(result, Constants.NORMAL_MESSAGE);
    }
}

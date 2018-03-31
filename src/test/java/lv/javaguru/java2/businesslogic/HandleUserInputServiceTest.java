package lv.javaguru.java2.businesslogic;

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
public class HandleUserInputServiceTest implements Constants {
    
    @Autowired private HandleUserInputService handleUserInputService;
    
    @Test
    public void shouldReturnEmptyConstant( ) {
        UserInputResponse response1 = handleUserInputService.handle( "" );
        byte result1 = response1.getCommand( );
        UserInputResponse response2 = handleUserInputService.handle( " " );
        byte result2 = response2.getCommand( );
        UserInputResponse response3 = handleUserInputService.handle( "  " );
        byte result3 = response3.getCommand( );
        
        assertEquals( result1, Constants.EMPTY_MESSAGE );
        assertEquals( result2, Constants.EMPTY_MESSAGE );
        assertEquals( result3, Constants.EMPTY_MESSAGE );
    }
    
    @Test
    public void shouldReturnBadCommandConstant() {
        UserInputResponse response = handleUserInputService.handle("/");
        byte result = response.getCommand();
        assertEquals(result, Constants.BAD_COMMAND);
    }
    
    @Test
    public void shouldReturnRefreshConstant() {
        UserInputResponse response = handleUserInputService.handle("/r");
        byte result = response.getCommand();
        assertEquals(result, Constants.GET_CHAT_HISTORY);
    }
    
    @Test
    public void shouldReturnPrintMessageConstant() {
        UserInputResponse response = handleUserInputService.handle("Hello");
        byte result = response.getCommand();
        assertEquals(result, Constants.NORMAL_MESSAGE);
    }
}

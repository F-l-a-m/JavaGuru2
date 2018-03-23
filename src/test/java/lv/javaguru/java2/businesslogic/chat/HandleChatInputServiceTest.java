package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class HandleChatInputServiceTest implements Constants {

    private HandleChatInputService handleChatInputService;
    private User user;

    @Before
    public void init() {
        UserService userService = Mockito.mock(UserService.class);
        user = Mockito.mock(User.class);
        handleChatInputService = new HandleChatInputService(userService);
    }

    @Test
    public void shouldReturnEmptyConstant() {
        Enum result1 = handleChatInputService.handle(user, "");
        Enum result2 = handleChatInputService.handle(user," ");
        Enum result3 = handleChatInputService.handle(user,"  ");

        assertEquals(result1, userActions.EMPTY_MESSAGE);
        assertEquals(result2, userActions.EMPTY_MESSAGE);
        assertEquals(result3, userActions.EMPTY_MESSAGE);
    }

    @Test
    public void shouldReturnBadCommandConstant() {
        Enum result = handleChatInputService.handle(user,"/");

        assertEquals(result, userActions.BAD_COMMAND);
    }

    @Test
    public void shouldReturnRefreshConstant() {
        Enum result = handleChatInputService.handle(user,"/r");

        assertEquals(result, userActions.REFRESH_CONSOLE);
    }

    @Test
    public void shouldReturnPrintMessageConstant() {
        Enum result = handleChatInputService.handle(user,"Hello");
        
        assertEquals(result, userActions.PRINT_MESSAGE);
    }
}

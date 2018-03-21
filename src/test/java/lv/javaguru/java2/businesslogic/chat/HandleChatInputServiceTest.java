package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class HandleChatInputServiceTest implements Constants {

    private HandleChatInputService handleChatInputService;

    @Before
    public void init() {
        Database database = Mockito.mock(Database.class);
        StringCache stringCache = Mockito.mock(StringCache.class);
        User user = Mockito.mock(User.class);
        MessageService messageService = Mockito.mock(MessageService.class);
        handleChatInputService = new HandleChatInputService(database, user, stringCache, messageService);
    }

    @Test
    public void shouldReturnEmptyConstant() {
        Enum result1 = handleChatInputService.handle("");
        Enum result2 = handleChatInputService.handle(" ");
        Enum result3 = handleChatInputService.handle("  ");

        assertEquals(result1, userActions.EMPTY_MESSAGE);
        assertEquals(result2, userActions.EMPTY_MESSAGE);
        assertEquals(result3, userActions.EMPTY_MESSAGE);
    }

    @Test
    public void shouldReturnBadCommandConstant() {
        Enum result = handleChatInputService.handle("/");

        assertEquals(result, userActions.BAD_COMMAND);
    }

    @Test
    public void shouldReturnRefreshConstant() {
        Enum result = handleChatInputService.handle("/r");

        assertEquals(result, userActions.REFRESH_CONSOLE);
    }

    @Test
    public void shouldReturnPrintMessageConstant() {
        Enum result = handleChatInputService.handle("Hello");
        
        assertEquals(result, userActions.PRINT_MESSAGE);
    }
}

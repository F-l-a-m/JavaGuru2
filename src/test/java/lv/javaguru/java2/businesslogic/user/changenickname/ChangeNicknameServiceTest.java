package lv.javaguru.java2.businesslogic.user.changenickname;

import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChangeNicknameServiceTest {

    private ChangeNicknameValidator changeNicknameValidator;
    private ChangeNicknameService changeNicknameService;
    private Database database;

    @Before
    public void init() {
        changeNicknameValidator = Mockito.mock(ChangeNicknameValidator.class);
        database = Mockito.mock(Database.class);
        changeNicknameService = new ChangeNicknameService(database, changeNicknameValidator);
    }

    @Test
    public void shouldReturnSuccess() {
        List<ChangeNicknameError> errors = new ArrayList<>();
        Mockito.when(changeNicknameValidator.validate("nickname"))
                .thenReturn(errors);
        User user = Mockito.mock(User.class);
        Mockito.when(database.getCurrentUser())
                .thenReturn(user);

        ChangeNicknameResponse response = changeNicknameService.changeUserNickname("nickname");

        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void shouldReturnFail() {
        List<ChangeNicknameError> errors = new ArrayList<>();
        errors.add(new ChangeNicknameError("error message"));
        Mockito.when(changeNicknameValidator.validate("nickname"))
                .thenReturn(errors);

        ChangeNicknameResponse response = changeNicknameService.changeUserNickname("nickname");

        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}
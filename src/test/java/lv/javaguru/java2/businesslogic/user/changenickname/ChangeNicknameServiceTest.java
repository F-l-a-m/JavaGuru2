/*
package lv.javaguru.java2.businesslogic.user.changenickname;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.user.ChangeNicknameValidator;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ChangeNicknameServiceTest {

    private ChangeNicknameValidator validator;
    private UserService userService;

    @Before
    public void init() {
        validator = Mockito.mock(ChangeNicknameValidator.class);
        userService = new UserService();
    }

    @Test
    public void shouldReturnSuccess() {
        List<Error> errors = new ArrayList<>();
        Mockito.when(validator.validate("nickname"))
                .thenReturn(errors);
        User user = Mockito.mock(User.class);
        */
/*Mockito.when(database.findUser(" "))
                .thenReturn(Optional.of(user));*//*


        Response response = userService.changeUserNickname(user.getNickname(), "nickname");

        assertEquals(response.isSuccess(), true);
        assertEquals(response.getErrors(), null);
    }

    @Test
    public void shouldReturnFail() {
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("error message"));
        Mockito.when(validator.validate("nickname"))
                .thenReturn(errors);
        User user = Mockito.mock(User.class);

        Response response = userService.changeUserNickname(user.getNickname(),"nickname");

        assertEquals(response.isSuccess(), false);
        assertEquals(response.getErrors(), errors);
    }
}
*/

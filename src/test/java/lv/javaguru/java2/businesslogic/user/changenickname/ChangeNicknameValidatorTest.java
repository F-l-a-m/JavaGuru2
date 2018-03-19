package lv.javaguru.java2.businesslogic.user.changenickname;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.ChangeNicknameValidator;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ChangeNicknameValidatorTest {

    private ChangeNicknameValidator changeNicknameValidator;

    @Before
    public void init() {
        changeNicknameValidator = new ChangeNicknameValidator();
    }

    @Test
    public void emptyNicknameShouldReturnError() {
        List<Error> errors = changeNicknameValidator.validate("");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname must not be empty");
    }

    @Test
    public void shortNicknameShouldReturnError() {
        List<Error> errors = changeNicknameValidator.validate("a");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname length should be 2 to 16 symbols");
    }

    @Test
    public void illegalSymbolsShouldReturnError() {
        List<Error> errors = changeNicknameValidator.validate("abc$");
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname contains illegal characters (letters and numbers only please)");
    }

    @Test
    public void illegalSymbolShouldReturnTwoErrors() {
        List<Error> errors = changeNicknameValidator.validate("$");
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname length should be 2 to 16 symbols");
        assertEquals(errors.get(1).getErrorMessage(), "Nickname contains illegal characters (letters and numbers only please)");
    }

}

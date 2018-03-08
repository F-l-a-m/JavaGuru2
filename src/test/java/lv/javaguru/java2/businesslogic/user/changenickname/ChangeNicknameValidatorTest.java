package lv.javaguru.java2.businesslogic.user.changenickname;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ChangeNicknameValidatorTest {

    private ChangeNicknameValidator changeNicknameValidator;

    @Test
    public void emptyNicknameShouldReturnError(){
        changeNicknameValidator = new ChangeNicknameValidator("");
        List<ChangeNicknameError> errors = changeNicknameValidator.validate();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname must not be empty");
    }

    @Test
    public void shortNicknameShouldReturnError(){
        changeNicknameValidator = new ChangeNicknameValidator("a");
        List<ChangeNicknameError> errors = changeNicknameValidator.validate();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname length should be 2 to 16 symbols");
    }

    @Test
    public void illegalSymbolsShouldReturnError(){
        changeNicknameValidator = new ChangeNicknameValidator("abc$");
        List<ChangeNicknameError> errors = changeNicknameValidator.validate();
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname contains illegal characters (letters and numbers only please)");
    }

    @Test
    public void illegalSymbolShouldReturnTwoErrors(){
        changeNicknameValidator = new ChangeNicknameValidator("$");
        List<ChangeNicknameError> errors = changeNicknameValidator.validate();
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getErrorMessage(), "Nickname length should be 2 to 16 symbols");
        assertEquals(errors.get(1).getErrorMessage(), "Nickname contains illegal characters (letters and numbers only please)");
    }

}

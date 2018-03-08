package lv.javaguru.java2.businesslogic.user.changenickname;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChangeNicknameValidator {

    private String nickname;
    private boolean isEmpty;

    public ChangeNicknameValidator(String nickname) {
        this.nickname = nickname;
        isEmpty = false;
    }

    public List<ChangeNicknameError> validate() {
        List<ChangeNicknameError> errors = new ArrayList<>();
        validateEmpty().ifPresent(errors::add);
        if (!isEmpty) validateLength().ifPresent(errors::add);
        if (!isEmpty) validateAllowedSymbols().ifPresent(errors::add);
        return errors;
    }

    private Optional<ChangeNicknameError> validateEmpty() {
        if (this.nickname == null || this.nickname.isEmpty()) {
            isEmpty = true;
            return Optional.of(new ChangeNicknameError("Nickname must not be empty"));
        }
        return Optional.empty();
    }

    private Optional<ChangeNicknameError> validateLength() {
        if (nickname.length() > 16 || nickname.length() < 2)
            return Optional.of(new ChangeNicknameError("Nickname length should be 2 to 16 symbols"));
        return Optional.empty();
    }

    private Optional<ChangeNicknameError> validateAllowedSymbols() {
        if (!nickname.matches("[0-9A-Za-z]+"))
            return Optional.of(new ChangeNicknameError("Nickname contains illegal characters (letters and numbers only please)"));
        return Optional.empty();
    }
}

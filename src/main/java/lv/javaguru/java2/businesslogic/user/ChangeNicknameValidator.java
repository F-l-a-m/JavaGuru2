package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChangeNicknameValidator {

    private String nickname;
    private boolean isEmpty;

    public List<Error> validate(String nickname) {
        this.nickname = nickname;
        isEmpty = false;
        List<Error> errors = new ArrayList<>();
        validateEmpty().ifPresent(errors::add);
        if (!isEmpty) validateLength().ifPresent(errors::add);
        if (!isEmpty) validateAllowedSymbols().ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateEmpty() {
        if (nickname == null || nickname.isEmpty()) {
            isEmpty = true;
            return Optional.of(new Error("Nickname must not be empty"));
        }
        return Optional.empty();
    }

    private Optional<Error> validateLength() {
        if (nickname.length() > 16 || nickname.length() < 2)
            return Optional.of(new Error("Nickname length should be 2 to 16 symbols"));
        return Optional.empty();
    }

    private Optional<Error> validateAllowedSymbols() {
        if (!nickname.matches("[0-9A-Za-z]+"))
            return Optional.of(new Error("Nickname contains illegal characters (letters and numbers only please)"));
        return Optional.empty();
    }
}

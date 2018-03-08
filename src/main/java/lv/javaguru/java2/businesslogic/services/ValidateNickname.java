package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.businesslogic.services.ValidationError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValidateNickname {

    String nickname;

    public ValidateNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<ValidationError> validate(){
        List<ValidationError> errors = new ArrayList<>();
        validateEmpty().ifPresent(errors::add);
        validateLength().ifPresent(errors::add);
        validateAllowedSymbols().ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validateEmpty (){
        if(this.nickname == null ||this.nickname.isEmpty())
            return Optional.of(new ValidationError("Nickname must not be empty"));
        return Optional.empty();
    }

    private Optional<ValidationError> validateLength(){
        if(nickname.length() > 16 || nickname.length() < 2)
            return Optional.of(new ValidationError("Nickname length should be 2 to 16 symbols"));
        return Optional.empty();
    }

    private Optional<ValidationError> validateAllowedSymbols(){
        if(!nickname.matches("[0-9A-Za-z]+"))
            return Optional.of(new ValidationError("Nickname contains illegal characters (letters and numbers only please)"));
        return Optional.empty();
    }
}

package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoomNameValidator {

    private String roomName;
    private boolean isEmpty;

    public List<Error> validate(String roomName) {
        this.roomName = roomName;
        isEmpty = false;
        List<Error> errors = new ArrayList<>();
        validateEmpty().ifPresent(errors::add);
        if (!isEmpty) validateLength().ifPresent(errors::add);
        if (!isEmpty) validateAllowedSymbols().ifPresent(errors::add);
        return errors;
    }

    private Optional<Error> validateEmpty() {
        if (roomName == null || roomName.isEmpty()) {
            isEmpty = true;
            return Optional.of(new Error("Room name must not be empty"));
        }
        return Optional.empty();
    }

    private Optional<Error> validateLength() {
        if (roomName.length() > 16 || roomName.length() < 2)
            return Optional.of(new Error("Room name length should be 2 to 16 symbols"));
        return Optional.empty();
    }

    private Optional<Error> validateAllowedSymbols() {
        if (!roomName.matches("[0-9A-Za-z]+"))
            return Optional.of(new Error("Room name contains illegal characters " +
                    "(letters and numbers only please)"));
        return Optional.empty();
    }
}

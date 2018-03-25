package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.domain.Message;

import java.util.List;

public class AddMessageResponse {

    private boolean success;
    private List<Error> errors;
    private Message message;

    public AddMessageResponse(boolean success, Message message, List<Error> errors) {
        this.success = success;
        this.errors = errors;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}

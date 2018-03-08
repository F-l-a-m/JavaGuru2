package lv.javaguru.java2.businesslogic.services;

import java.util.List;

public class Response {

    private boolean success;
    private List<ValidationError> errors;

    public Response(boolean success, List<ValidationError> errors) {
        this.success = success;
        this.errors = errors;
    }

    public boolean isSuccess(){
        return success;
    }

    public List<ValidationError> getErrors(){
        return errors;
    }
}

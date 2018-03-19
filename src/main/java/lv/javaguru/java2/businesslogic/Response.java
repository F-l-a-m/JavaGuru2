package lv.javaguru.java2.businesslogic;

import java.util.List;

public class Response {

    private final boolean success;
    private final List<Error> errors;

    public Response(boolean success, List<Error> errors) {
        this.success = success;
        this.errors = errors;
    }

    public boolean isSuccess(){
        return success;
    }

    public List<Error> getErrors(){
        return errors;
    }
}

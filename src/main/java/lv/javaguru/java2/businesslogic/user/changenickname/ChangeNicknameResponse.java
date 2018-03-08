package lv.javaguru.java2.businesslogic.user.changenickname;

import java.util.List;

public class ChangeNicknameResponse {

    private boolean success;
    private List<ChangeNicknameError> errors;

    public ChangeNicknameResponse(boolean success, List<ChangeNicknameError> errors) {
        this.success = success;
        this.errors = errors;
    }

    public boolean isSuccess(){
        return success;
    }

    public List<ChangeNicknameError> getErrors(){
        return errors;
    }
}

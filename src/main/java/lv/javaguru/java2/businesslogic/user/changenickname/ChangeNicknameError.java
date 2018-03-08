package lv.javaguru.java2.businesslogic.user.changenickname;

public class ChangeNicknameError {

    private String errorMessage;

    public ChangeNicknameError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

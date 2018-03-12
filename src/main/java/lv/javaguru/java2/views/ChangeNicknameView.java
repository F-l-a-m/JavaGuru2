package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameResponse;
import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameService;
import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.businesslogic.chat.LastChatInput;

public class ChangeNicknameView implements View {

    private LastChatInput lastChatInput;
    private ChangeNicknameService changeNicknameService;
    private ChangeNicknameValidator changeNicknameValidator;

    public ChangeNicknameView(Database database, LastChatInput lastChatInput) {
        this.lastChatInput = lastChatInput;
        this.changeNicknameValidator = new ChangeNicknameValidator();
        this.changeNicknameService = new ChangeNicknameService(database, changeNicknameValidator);
    }

    @Override
    public void execute() {

        String nickname = lastChatInput.getUserInput();
        ChangeNicknameResponse changeNicknameResponse = changeNicknameService.changeUserNickname(nickname);
        if(changeNicknameResponse.isSuccess()){
            System.out.println("User nickname set to \'" + nickname + '\'');
        }
        else{
            changeNicknameResponse.getErrors()
                    .forEach(error -> System.out.println(error.getErrorMessage()));
        }

        System.out.println();
    }
}

package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameResponse;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.businesslogic.chat.LastUserInput;

public class ChangeNicknameView implements View {

    private UserService userService;
    private LastUserInput lastUserInput;

    public ChangeNicknameView(Database database, LastUserInput lastUserInput) {
        this.userService = new UserService(database);
        this.lastUserInput = lastUserInput;
    }

    @Override
    public void execute() {

        String nickname = lastUserInput.getUserInput();

        ChangeNicknameResponse changeNicknameResponse =  userService.changeUserNickname(nickname);
        if(changeNicknameResponse.isSuccess()){
            System.out.println("User nickname set to \'" + nickname + '\'');
        }
        else{
            changeNicknameResponse.getErrors().forEach(error -> {
                System.out.println(error.getErrorMessage());
            });
        }

        System.out.println();
    }
}

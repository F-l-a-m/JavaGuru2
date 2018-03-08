package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.services.Response;
import lv.javaguru.java2.businesslogic.services.UserService;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.LastChatMessage;

public class ChangeNicknameView implements View {

    private UserService userService;
    private LastChatMessage lastChatMessage;

    public ChangeNicknameView(Database database, LastChatMessage lastChatMessage) {
        this.userService = new UserService(database);
        this.lastChatMessage = lastChatMessage;
    }

    @Override
    public void execute() {

        String nickname = lastChatMessage.getUserInput();
        Response response =  userService.changeUserNickname(nickname);
        if(response.isSuccess()){
            System.out.println("User nickname set to \'" + nickname + '\'');
        }
        else{
            response.getErrors().forEach(error -> {
                System.out.println(error.getErrorMessage());
            });
        }
        System.out.println();
    }
}

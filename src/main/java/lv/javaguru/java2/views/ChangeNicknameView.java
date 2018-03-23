package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.domain.User;

public class ChangeNicknameView implements View {
    
    private User user;
    private UserService userService;

    public ChangeNicknameView(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }

    @Override
    public void execute() {
        String nickname = userService.getUserInput(user);
        Response response = userService.changeUserNickname(user.getNickname(), nickname);
        if(response.isSuccess()){
            user.setNickname(nickname);
            System.out.println("User nickname set to \'" + nickname + '\'');
        } else {
            response.getErrors()
                    .forEach(error -> System.out.println(error.getErrorMessage()));
        }
        System.out.println();
    }
}

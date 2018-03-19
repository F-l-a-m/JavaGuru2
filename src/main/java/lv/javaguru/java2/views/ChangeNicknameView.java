package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.businesslogic.StringCache;

public class ChangeNicknameView implements View {

    private final StringCache stringCache;
    private final UserService userService;
    private final User user;

    public ChangeNicknameView(Database database, User user, StringCache stringCache) {
        this.stringCache = stringCache;
        this.userService = new UserService(database);
        this.user = user;
    }

    @Override
    public void execute() {

        String nickname = stringCache.getTemporaryString();
        Response response = userService.changeUserNickname(user, nickname);
        if(response.isSuccess()){
            System.out.println("User nickname set to \'" + nickname + '\'');
        }
        else{
            response.getErrors()
                    .forEach(error -> System.out.println(error.getErrorMessage()));
        }

        System.out.println();
    }
}

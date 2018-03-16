/*
package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameService;
import lv.javaguru.java2.businesslogic.user.changenickname.ChangeNicknameValidator;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.businesslogic.chat.StringCache;

public class ChangeNicknameView implements View {

    private StringCache stringCache;
    private ChangeNicknameService changeNicknameService;
    private ChangeNicknameValidator changeNicknameValidator;

    public ChangeNicknameView(Database database, StringCache stringCache) {
        this.stringCache = stringCache;
        this.changeNicknameValidator = new ChangeNicknameValidator();
        this.changeNicknameService = new ChangeNicknameService(database, changeNicknameValidator);
    }

    @Override
    public void execute() {

        String nickname = stringCache.getTemporaryString();
        Response response = changeNicknameService.changeUserNickname(nickname);
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
*/

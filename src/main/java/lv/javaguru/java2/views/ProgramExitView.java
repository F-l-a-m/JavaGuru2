package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.domain.User;

public class ProgramExitView implements View {
    
    User user;
    UserService userService;
    
    public ProgramExitView( User user, UserService userService ) {
        this.user = user;
        this.userService = userService;
    }
    
    @Override
    public void execute() {
        userService.logOut(user);
        System.out.println("Good bye!");
        System.exit(0);
    }
}

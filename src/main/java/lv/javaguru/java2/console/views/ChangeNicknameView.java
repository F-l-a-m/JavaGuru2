package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.console.businesslogic.user.User_ChangeNicknameResponse;
import lv.javaguru.java2.console.businesslogic.user.User_ChangeNicknameService;
import lv.javaguru.java2.console.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeNicknameView implements View {
    
    @Autowired private User_ChangeNicknameService changeNicknameService;
    @Autowired private ConsoleSession session;
    
    @Override
    public void execute( ) {
        User user = session.getUser( );
        String userConsoleInput = session.getUserConsoleInputResponse( ).getData( );
        
        String oldNickname = user.getNickname( );
        String newNickname = userConsoleInput;
        User_ChangeNicknameResponse response =
                changeNicknameService.changeNickname( oldNickname, newNickname );
        if ( response.isSuccess( ) ) {
            user = response.getUser( );
            session.setUser( user );
            System.out.println( oldNickname + " successfully changed nick to " + user.getNickname( ) );
        } else {
            printErrors( response.getErrors( ) );
        }
    }
}

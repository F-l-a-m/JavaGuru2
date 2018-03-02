package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.models.ChatLine;
import lv.javaguru.java2.Globals;
import lv.javaguru.java2.businesslogic.models.Timestamp;
import lv.javaguru.java2.businesslogic.models.User;

public class HandleUserInput {

    public Enum CheckLine(String input){
        if(input.equals("")){
            ChatLine line = new ChatLine(
                    new Timestamp().getTimestamp(),
                    Globals.getUser().getNickname(),
                    input
            );
            Globals.SetLine(line);
            return Constants.userActions.EMPTY_MESSAGE;
        }
        else if(input.equals("/quit")){
            //quit app
            System.exit(0);
            return Constants.userActions.QUIT;
        }
        else if(input.equals("/nick")){
            return Constants.userActions.CHANGE_NICK;
        }
        else if(input.equals("/r")){
            return Constants.userActions.REFRESH_CONSOLE;
        }
        else { // Usual chat message
            ChatLine line = new ChatLine(
                    new Timestamp().getTimestamp(),
                    Globals.getUser().getNickname(),
                    input
            );
            Globals.SetLine(line);
            return Constants.userActions.MESSAGE;
        }
    }
}

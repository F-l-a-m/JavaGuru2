package lv.javaguru.java2;

import lv.javaguru.java2.database.*;
import lv.javaguru.java2.models.*;
import lv.javaguru.java2.views.*;
import java.util.*;

public class ChatApplication {

    public static void main(String[] args) {

        ChatDatabase database = new ChatHistoryInMemoryDatabase();
        Timestamp timestamp = new Timestamp();

        View chatCommandsView = new ChatCommandsView();
        View addChatLineView = new AddChatLineView(database);
        View changeNicknameView = new ChangeNicknameView();
        View programExitView = new ProgramExitView();
        View badCommandView = new BadCommandView();
        View refreshConsoleView = new RefreshConsoleView(database);

        // ready actions
        Map<Enum, View> actionMap = new HashMap<>();
        actionMap.put(Constants.userActions.MESSAGE, addChatLineView); // +
        actionMap.put(Constants.userActions.CHANGE_NICK, changeNicknameView); // -
        actionMap.put(Constants.userActions.QUIT, programExitView); // -
        actionMap.put(Constants.userActions.BAD_COMMAND, badCommandView); // -
        actionMap.put(Constants.userActions.REFRESH_CONSOLE, refreshConsoleView);

        chatCommandsView.execute();

        // get message
        while(true) {
            String userInput;
            userInput = readUserInput();
            Enum e;
            e = HandleUserMessage(userInput);
            View view = actionMap.get(e);
            view.execute();
        }

    }

    private static String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    // decide action
    private static Enum HandleUserMessage(String userInputString){
        if(userInputString.equals("")){
            //handle ?
            return Constants.userActions.MESSAGE;
        }
        else if(userInputString.equals("/quit")){
            //quit app
            System.exit(0);
            return Constants.userActions.QUIT;
        }
        else if(userInputString.equals("/nick")){
            //setnick
            /*System.out.println("Please enter your username");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            user.setNickname(input);*/
            return Constants.userActions.CHANGE_NICK;
        }
        else if(userInputString.equals("/r")){
            return Constants.userActions.REFRESH_CONSOLE;
        }
        else { // Usual chat message
            User user = new User();
            ChatLine line = new ChatLine(
                    new Timestamp().getTimestamp(),
                    user.getNickname(),
                    userInputString
            );
            GlobalLine.SetLine(line);
            return Constants.userActions.MESSAGE;
        }
    }

}

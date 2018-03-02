package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.HandleUserInput;
import lv.javaguru.java2.businesslogic.models.Timestamp;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;

import java.util.*;

public class ChatApplication {

    public static void main(String[] args) {

        ChatDatabase database = new ChatHistoryInMemoryDatabase();
        HandleUserInput handleUserInput = new HandleUserInput();

        View chatCommandsView = new ChatCommandsView();
        View addChatLineView = new AddChatLineView(database);
        View changeNicknameView = new ChangeNicknameView();
        View programExitView = new ProgramExitView();
        View badCommandView = new BadCommandView();
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView();

        // ready actions
        Map<Enum, View> actionMap = new HashMap<>();
        actionMap.put(Constants.userActions.MESSAGE, addChatLineView); // +
        actionMap.put(Constants.userActions.EMPTY_MESSAGE, emptyMessageView); // +
        actionMap.put(Constants.userActions.CHANGE_NICK, changeNicknameView); // +
        actionMap.put(Constants.userActions.REFRESH_CONSOLE, refreshConsoleView); // +
        actionMap.put(Constants.userActions.BAD_COMMAND, badCommandView); // -
        actionMap.put(Constants.userActions.QUIT, programExitView); // -

        chatCommandsView.execute();

        // get message
        while (true) {
            String line = readLine();
            Enum e = handleUserInput.CheckLine(line);
            View view = actionMap.get(e);
            view.execute();
        }

    }

    private static String readLine() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

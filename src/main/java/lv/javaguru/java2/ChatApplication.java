package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.services.HandleUserInputService;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;
import java.util.*;

public class ChatApplication {

    public static void main(String[] args) {

        ChatDatabase database = new ChatHistoryInMemoryDatabase();
        HandleUserInputService handleUserInputService = new HandleUserInputService(database);

        View chatCommandsPrintView = new ChatCommandsPrintView();
        View printLastChatLineView = new PrintLastChatLineView(database);
        View changeNicknameView = new ChangeNicknameView();
        View programExitView = new ProgramExitView();
        View badCommandView = new BadCommandView();
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView();

        // ready actions
        Map<Enum, View> actionMap = new HashMap<>();
        actionMap.put(Constants.userActions.MESSAGE, printLastChatLineView); // +
        actionMap.put(Constants.userActions.EMPTY_MESSAGE, emptyMessageView); // +
        actionMap.put(Constants.userActions.CHANGE_NICK, changeNicknameView); // +
        actionMap.put(Constants.userActions.REFRESH_CONSOLE, refreshConsoleView); // +
        actionMap.put(Constants.userActions.BAD_COMMAND, badCommandView); // +
        actionMap.put(Constants.userActions.QUIT, programExitView); // +

        chatCommandsPrintView.execute();

        // get message
        while (true) {
            String line = readLine();
            Enum e = handleUserInputService.CheckLine(line);
            View view = actionMap.get(e);
            view.execute();
        }

    }

    private static String readLine() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

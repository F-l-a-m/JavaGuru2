package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.services.*;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;
import java.util.*;

public class ChatApplication {

    public static void main(String[] args) {

        Database database = new InMemoryDatabase();
        HandleUserInputService handleUserInputService = new HandleUserInputService(database);
        UserService userService = new UserService(database);
        // Request to create new user
        userService.createNewUser();
        // user service creates new user
        // saves it to db

        View chatCommandsPrintView = new ChatCommandsPrintView();
        View printLastChatLineView = new PrintLastChatLineView(database);
        View changeNicknameView = new ChangeNicknameView(database);
        View programExitView = new ProgramExitView();
        View badCommandView = new BadCommandView();
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView(database);

        // All available actions depending on user input
        Map<Enum, View> actionMap = new HashMap<>();
        actionMap.put(Constants.userActions.MESSAGE, printLastChatLineView);
        actionMap.put(Constants.userActions.EMPTY_MESSAGE, emptyMessageView);
        actionMap.put(Constants.userActions.CHANGE_NICK, changeNicknameView);
        actionMap.put(Constants.userActions.REFRESH_CONSOLE, refreshConsoleView);
        actionMap.put(Constants.userActions.BAD_COMMAND, badCommandView);
        actionMap.put(Constants.userActions.QUIT, programExitView);

        // Print available chat commands
        chatCommandsPrintView.execute();

        // Get message from user
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

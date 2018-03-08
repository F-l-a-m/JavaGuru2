package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.chat.*;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatApplication {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.initialize();
        launcher.start();
    }
}

class Launcher implements Constants {

    private Database database;
    private LastUserInput lastUserInput;
    private HandleUserInputService handleUserInputService;
    private UserService userService;
    private Map<Enum, View> actionMap;

    public void initialize() {

        database = new InMemoryDatabase();
        lastUserInput = new LastUserInput(); // only user input string
        handleUserInputService = new HandleUserInputService(database, lastUserInput);
        userService = new UserService(database);
        // user service creates new user, saves it to db
        userService.createNewUser();
        actionMap = new HashMap<>();

        View chatCommandsPrintView = new PrintAvailableChatCommandsView();
        View printLastChatLineView = new PrintLastChatLineView(database);
        View changeNicknameView = new ChangeNicknameView(database, lastUserInput);
        View programExitView = new ProgramExitView();
        View badCommandView = new BadCommandView();
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView(database);

        // All available actions depending on user input
        actionMap.put(userActions.PRINT_MESSAGE, printLastChatLineView);
        actionMap.put(userActions.EMPTY_MESSAGE, emptyMessageView);
        actionMap.put(userActions.CHANGE_NICK, changeNicknameView);
        actionMap.put(userActions.REFRESH_CONSOLE, refreshConsoleView);
        actionMap.put(userActions.BAD_COMMAND, badCommandView);
        actionMap.put(userActions.QUIT, programExitView);

        // Print available chat commands
        chatCommandsPrintView.execute();
    }

    public void start() {
        // Get message from user
        while (true) {
            String userInput = readLine();
            Enum action = handleUserInputService.handle(userInput);
            View view = actionMap.get(action);
            view.execute();
        }
    }

    private String readLine() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

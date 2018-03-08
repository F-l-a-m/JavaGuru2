package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.services.*;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;
import java.util.*;

public class ChatApplication {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.initialize();
        launcher.start();
    }
}

class Launcher implements Constants {

    private Database database = new InMemoryDatabase();
    LastChatMessage lastChatMessage = new LastChatMessage();
    private HandleUserInputService handleUserInputService = new HandleUserInputService(database, lastChatMessage);
    private UserService userService = new UserService(database);
    Map<Enum, View> actionMap = new HashMap<>();


    public void initialize(){
        // Request to create new user
        userService.createNewUser();
        // user service creates new user
        // saves it to db

        View chatCommandsPrintView = new PrintAvailableChatCommandsView();
        View printLastChatLineView = new PrintLastChatLineView(database);
        View changeNicknameView = new ChangeNicknameView(database, lastChatMessage);
        View programExitView = new ProgramExitView();
        View badCommandView = new BadCommandView();
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView(database);

        // All available actions depending on user input
        actionMap.put(userActions.MESSAGE, printLastChatLineView);
        actionMap.put(userActions.EMPTY_MESSAGE, emptyMessageView);
        actionMap.put(userActions.CHANGE_NICK, changeNicknameView);
        actionMap.put(userActions.REFRESH_CONSOLE, refreshConsoleView);
        actionMap.put(userActions.BAD_COMMAND, badCommandView);
        actionMap.put(userActions.QUIT, programExitView);

        // Print available chat commands
        chatCommandsPrintView.execute();
    }

    public void start(){
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

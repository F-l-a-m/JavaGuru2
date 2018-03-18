package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.chat.*;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.businesslogic.user.CurrentUser;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;

import java.util.Map;
import java.util.Optional;
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
    private StringCache stringCache;
    //private HandleChatInputService handleChatInputService;
    private UserService userService;
    private Map<Enum, View> actionMap;
    private ChatRoomService chatRoomService;

    public void initialize() {

        database = new ChatRealDatabase();

        // user service creates new user, saves it to db
        userService = new UserService(database);
        //userService.createNewUser();

        // room service creates / opens guest room
        chatRoomService = new ChatRoomService(database);
        chatRoomService.initializeGuestRoom();



        Optional<User> userById = database.getUserById(Integer.toUnsignedLong(3));
        if(userById.isPresent()){
            User u = userById.get();
            System.out.println(u.getNickname() + ";  id: " + u.getId());

            userService.addUserToChatRoom("Guest room", u);
        }



        /*Optional<User> user4 = database.getUserById(Integer.toUnsignedLong(4));
        if(user4.isPresent()){
            User user = user4.get();
            System.out.println(user.getNickname() + ";  id: " + user.getId());
        }


        Optional<User> userFromDB = database.getUserByNickname("guest6");
        if(userFromDB.isPresent()){
            User user = userFromDB.get();
            System.out.println(user.getNickname() + ";  id: " + user.getId());
        }*/












        stringCache = new StringCache(); // data for views
        //handleChatInputService = new HandleChatInputService(database, stringCache);

        /*View chatCommandsPrintView = new PrintAvailableChatCommandsView();
        View printLastChatLineView = new PrintLastChatLineView(database);
        View changeNicknameView = new ChangeNicknameView(database, stringCache);
        View programExitView = new ProgramExitView();
        View badCommandView = new BadCommandView();
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView(database);
        View joinChatRoomView = new JoinChatRoomView(database, stringCache);

        // All available actions depending on user input
        actionMap = new HashMap<>();
        actionMap.put(userActions.PRINT_MESSAGE, printLastChatLineView);
        actionMap.put(userActions.EMPTY_MESSAGE, emptyMessageView);
        actionMap.put(userActions.CHANGE_NICK, changeNicknameView);
        actionMap.put(userActions.REFRESH_CONSOLE, refreshConsoleView);
        actionMap.put(userActions.BAD_COMMAND, badCommandView);
        actionMap.put(userActions.QUIT, programExitView);
        actionMap.put(userActions.JOIN_CHAT_ROOM, joinChatRoomView);

        // Print available chat commands
        chatCommandsPrintView.execute();*/

    }

    public void start() {

        /*// Get message from user
        while (true) {
            String userInput = readLine();
            Enum action = handleChatInputService.handle(userInput);
            View view = actionMap.get(action);
            view.execute();
        }*/

    }

    private String readLine() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}

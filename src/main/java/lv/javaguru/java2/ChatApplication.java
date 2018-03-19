package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.chat.*;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;

import java.util.*;

class ChatApplication {
    
    public static void main(String[] args) {
        Launcher launcher = new Launcher( );
        launcher.initialize( );
        launcher.start( );
    }
}

class Launcher implements Constants {
    
    private HandleChatInputService handleChatInputService;
    private Map<Enum, View> actionMap;
    
    public void initialize( ) {
    
        Database database = new ChatRealDatabase( );
        StringCache stringCache = new StringCache( );
        UserService userService;
        ChatRoomService chatRoomService;
        
        // user service creates new user, saves it to db
        userService = new UserService(database);
        User guestUser = userService.createNewGuest( );
        
        // room service creates / opens guest room
        chatRoomService = new ChatRoomService(database);
        CurrentRoom.setRoom(chatRoomService.initializeGuestRoom( ));
    
        
        
        // handles all user input
        handleChatInputService = new HandleChatInputService(database, guestUser, stringCache);
        
        View chatCommandsPrintView = new PrintAvailableChatCommandsView( );
        View printMessageView = new PrintMessageView(database);
        View changeNicknameView = new ChangeNicknameView(database, guestUser, stringCache);
        View programExitView = new ProgramExitView( );
        View badCommandView = new BadCommandView( );
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView(guestUser);
        View joinChatRoomView = new JoinChatRoomView(database, guestUser, stringCache);
        
        // All available actions depending on user input
        actionMap = new HashMap<>( );
        actionMap.put(userActions.PRINT_MESSAGE, printMessageView);
        actionMap.put(userActions.EMPTY_MESSAGE, emptyMessageView);
        actionMap.put(userActions.CHANGE_NICK, changeNicknameView);
        actionMap.put(userActions.REFRESH_CONSOLE, refreshConsoleView);
        actionMap.put(userActions.BAD_COMMAND, badCommandView);
        actionMap.put(userActions.QUIT, programExitView);
        actionMap.put(userActions.JOIN_CHAT_ROOM, joinChatRoomView);
        
        // Print available chat commands
        chatCommandsPrintView.execute( );
        
    }
    
    public void start( ) {
        
        // Get message from user
        while (true) {
            String userInput = readLine( );
            Enum action = handleChatInputService.handle(userInput);
            View view = actionMap.get(action);
            view.execute( );
        }
        
    }
    
    private String readLine( ) {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine( );
    }
}

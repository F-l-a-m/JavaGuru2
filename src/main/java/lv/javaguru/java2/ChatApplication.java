package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.chat.*;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.businesslogic.user.CurrentUser;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.views.*;

import java.util.*;

class ChatApplication implements Constants {
    
    private static HandleChatInputService handleChatInputService;
    private static Map<Enum, View> actionMap;
    
    public static void main(String[] args) {
        initialize( );
        start( );
    }
    
    private static void start( ) {
        
        // Get message from user
        while (true) {
            String userInput = readLine( );
            Enum action = handleChatInputService.handle(userInput);
            View view = actionMap.get(action);
            view.execute( );
        }
        
    }
    
    private static String readLine( ) {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine( );
    }
    
    private static void initialize( ) {
        
        final Database database = new ChatRealDatabase( );
        final UserService userService = new UserService(database);
        final ChatRoomService chatRoomService = new ChatRoomService(database);
    
        // global string for usage in views
        StringCache stringCache = new StringCache( );
        
        // user service creates new user, saves it to db
        CurrentUser.setUser(userService.initializeNewGuest( ));
        
        // room service creates / opens guest room
        CurrentRoom.setRoom(chatRoomService.initializeGuestRoom( ));
        
        
        // handles all user input
        handleChatInputService = new HandleChatInputService(database, CurrentUser.getUser(), stringCache);
        
        // all views
        View chatCommandsPrintView = new PrintAvailableChatCommandsView( );
        View printMessageView = new PrintMessageView(database);
        View changeNicknameView = new ChangeNicknameView(database, CurrentUser.getUser(), stringCache);
        View programExitView = new ProgramExitView( );
        View badCommandView = new BadCommandView( );
        View refreshConsoleView = new RefreshConsoleView(database);
        View emptyMessageView = new EmptyMessageView(CurrentUser.getUser());
        View joinChatRoomView = new JoinChatRoomView(database, CurrentUser.getUser(), stringCache);
        View listAllRoomsView = new ListAllRoomsView(database);
        View leaveChatRoomView = new LeaveChatRoomView(database, CurrentUser.getUser());
        
        // all available actions depending on user input
        actionMap = new HashMap<>( );
        actionMap.put(userActions.PRINT_MESSAGE, printMessageView);
        actionMap.put(userActions.EMPTY_MESSAGE, emptyMessageView);
        actionMap.put(userActions.CHANGE_NICK, changeNicknameView);
        actionMap.put(userActions.REFRESH_CONSOLE, refreshConsoleView);
        actionMap.put(userActions.BAD_COMMAND, badCommandView);
        actionMap.put(userActions.QUIT, programExitView);
        actionMap.put(userActions.JOIN_CHAT_ROOM, joinChatRoomView);
        actionMap.put(userActions.LIST_CHAT_ROOMS, listAllRoomsView);
        actionMap.put(userActions.LEAVE_CHAT_ROOM, leaveChatRoomView);
        
        // Print available chat commands
        chatCommandsPrintView.execute( );
    }
}

package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.chat.*;
import lv.javaguru.java2.businesslogic.room.*;
import lv.javaguru.java2.businesslogic.user.*;
import lv.javaguru.java2.database.*;
import lv.javaguru.java2.domain.ChatRoom;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.views.*;

import java.util.*;

class ChatApplication implements Constants {
    
    private static HandleChatInputService handleChatInputService;
    private static Map<Enum, View> actionMap;
    private static User user;
    private static ChatRoom room;
    
    public static void main( String[] args ) {
        initialize( );
        start( );
    }
    
    private static void initialize( ) {
        
        final Database database = new ChatRealDatabase( );
        final ChangeNicknameValidator validator = new ChangeNicknameValidator( );
        final UserService userService = new UserService(database, validator);
        final ChatRoomService chatRoomService = new ChatRoomService(database);
        final MessageService messageService = new MessageService(database);
        
        // global string for usage in views
        StringCache stringCache = new StringCache( );
        
        // print all available chat commands once
        View chatCommandsPrintView = new PrintAvailableChatCommandsView( );
        chatCommandsPrintView.execute( );
        
        // ask for user nickname
        System.out.print("Please enter your nickname: ");
        String nickname = readLine( );
        user = userService.login(nickname);
        
        // room service creates / opens guest room
        room = chatRoomService.initializeGuestRoom(user.getNickname( ));
        ActiveRoom activeRoom = new ActiveRoom();
        activeRoom.setRoom(room);
        
        // add user to default chat room
        userService.addUserToChatRoom(user, "Guest room");
        
        // handles all user input
        handleChatInputService = new HandleChatInputService(userService);
        
        // all views
        View printMessageView = new PrintMessageView(user, userService, activeRoom, messageService);
        View changeNicknameView = new ChangeNicknameView(user, userService);
        View programExitView = new ProgramExitView( );
        View badCommandView = new BadCommandView( );
        View refreshConsoleView = new RefreshConsoleView(messageService, activeRoom);
        View emptyMessageView = new EmptyMessageView(user);
        View joinChatRoomView = new JoinChatRoomView(user, userService, chatRoomService, activeRoom);
        View listAllRoomsView = new ListAllRoomsView(database);
        View leaveChatRoomView = new LeaveChatRoomView(user, userService, activeRoom);
        
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
    }
    
    private static void start( ) {
        
        // Get message from user
        while (true) {
            String userInput = readLine( );
            Enum action = handleChatInputService.handle(user, userInput);
            View view = actionMap.get(action);
            view.execute( );
        }
    }
    
    private static String readLine( ) {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine( );
    }
}

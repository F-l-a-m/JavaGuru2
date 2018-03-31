package lv.javaguru.java2.views;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.UserInputResponse;
import lv.javaguru.java2.businesslogic.HandleUserInputService;
import lv.javaguru.java2.businesslogic.message.*;
import lv.javaguru.java2.businesslogic.room.*;
import lv.javaguru.java2.businesslogic.user.*;
import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MainView implements View, Constants {
    
    @Autowired private AddMessageService addMessageService;
    @Autowired private AddUserToRoomService addUserToRoomService;
    @Autowired private FindUserInRoomService findUserInRoomService;
    @Autowired private HandleUserInputService handleUserInputService;
    @Autowired private InitializeUserService initializeUserService;
    @Autowired private JoinCreateRoomService joinCreateRoomService;
    @Autowired private GetAllChatHistoryService getAllChatHistoryService;
    
    private ApplicationContext applicationContext;
    
    public MainView( ApplicationContext applicationContext ) {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void execute( ) {
        // initialize user
        Scanner sc = new Scanner( System.in );
        User user = null;
        String nickname = null;
        boolean success = false;
        while ( !success ) {
            System.out.println( );
            System.out.print( "Please enter your nickname: " );
            nickname = sc.nextLine( );
            
            InitializeUserResponse initializeUserResponse = initializeUserService.init( nickname );
            if ( initializeUserResponse.isSuccess( ) ) {
                user = initializeUserResponse.getUser( );
                success = true;
            } else
                printErrors( initializeUserResponse.getErrors( ) );
        }
        
        // initialize guest room
        String roomName = "GuestRoom";
        Room room = null;
        JoinCreateRoomResponse joinCreateRoomResponse = joinCreateRoomService.init( roomName, nickname );
        if ( joinCreateRoomResponse.isSuccess( ) ) {
            room = joinCreateRoomResponse.getRoom( );
        } else {
            printErrors( joinCreateRoomResponse.getErrors( ) );
        }
        
        // check if user is already in that room
        boolean isUserAlreadyInThatRoom = findUserInRoomService.findUserInRoom( user.getId( ), roomName );
        if ( !isUserAlreadyInThatRoom )
            addUserToRoomService.add( user, room );
        System.out.println( "\nYou are now chatting in '" + roomName + "'" );
        
        // user and room initialized
        // print help for users
        applicationContext.getBean( PrintAvailableChatCommandsView.class ).execute( );
        
        // ready to get user input
        System.out.println( "\n[info] Entering while(true) loop" ); //debug info, delete in prod
        while ( true ) {
            System.out.println( "\n[info] Ready, waiting for your input" ); //debug info, delete in prod
            
            String input = sc.nextLine( );
            UserInputResponse userInputResponse = handleUserInputService.handle( input );
            
            switch ( userInputResponse.getCommand( ) ) {
                case Constants.QUIT_APP:
                    //new ProgramExitView( ).execute( );
                    applicationContext.getBean( ProgramExitView.class ).execute( );
                    break;
                
                case Constants.EMPTY_MESSAGE:
                    // [2018/03/26 12:12] username:
                    new EmptyMessageView( nickname ).execute( );
                    // Тут из-за передачи nickname не получается через getBean
                    break;
                
                case Constants.NORMAL_MESSAGE:
                    // Это хотелось бы вынести в отдельный View
                    AddMessageResponse addMessageResponse = addMessageService.addMessage(
                            userInputResponse.getData( ),
                            nickname,
                            room.getId( )
                    );
                    if ( addMessageResponse.isSuccess( ) ) {
                        System.out.println( addMessageResponse.getMessage( ) );
                    } else {
                        printErrors( addMessageResponse.getErrors( ) );
                    }
                    break;
                
                case Constants.JOIN_ROOM:
                    // Это хотелось бы вынести в отдельный View
                    // validate and join/create
                    joinCreateRoomResponse = joinCreateRoomService.init( userInputResponse.getData( ), nickname );
                    if ( joinCreateRoomResponse.isSuccess( ) ) {
                        room = joinCreateRoomResponse.getRoom( ); // но нужно как-то вернуть room, от него зависят
                        // другие switch case
                        System.out.println( "User '" + nickname + "' joined room '" + room.getName( ) + "'." );
                    } else {
                        printErrors( joinCreateRoomResponse.getErrors( ) );
                    }
                    break;
                
                case Constants.BAD_COMMAND:
                    applicationContext.getBean( BadCommandView.class ).execute( );
                    break;
                
                case Constants.LIST:
                    applicationContext.getBean( ListAllRoomsView.class ).execute( );
                    break;
                
                case Constants.CHANGE_NICKNAME:
                    // тут та же проблема что и с room,
                    // user будет нужен в других местах, например для нового сообщения
                    break;
                
                case Constants.GET_CHAT_HISTORY:
                    // new PrintChatHistoryInRoomView( room ).execute( );
                    // applicationContext.getBean( PrintChatHistoryInRoomView.class ).execute( );
                    // No qualifying bean of type 'lv.javaguru.java2.domain.Room' available
                    // :(
                    // И как тут быть, если код ниже хочеться вынести отдельно ?
                    
                    // Clear console
                    System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" );
                    // Print whole message history in given chat room
                    GetAllChatHistoryResponse response = getAllChatHistoryService.go( room );
                    List<Message> messages = response.getChatHistory( );
                    messages.forEach( System.out::println );
                    break;
            }
        }
    }
    
    private void printErrors( List<Error> errors ) {
        errors.forEach( error -> System.out.println( "Error message = " + error.getErrorMessage( ) ) );
    }
}

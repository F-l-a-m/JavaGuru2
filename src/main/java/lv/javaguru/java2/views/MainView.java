package lv.javaguru.java2.views;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.User_InputResponse;
import lv.javaguru.java2.businesslogic.user.User_InputService;
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
    
    @Autowired private User_AddService userAddService;
    @Autowired private Message_AddService messageAddService;
    @Autowired private User_AddToRoomService userAddToRoomService;
    @Autowired private User_FindInRoomService userFindInRoomService;
    @Autowired private User_InputService userInputService;
    @Autowired private Room_JoinOrCreateService roomJoinCreateService;
    @Autowired private Message_GetChatHistoryService messageGetChatHistoryService;
    @Autowired private User_GetAListOfJoinedRoomsService userGetAListOfJoinedRoomsService;
    @Autowired private Room_NameValidator roomNameValidator;
    
    private ApplicationContext applicationContext;
    
    public MainView( ApplicationContext applicationContext ) {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void execute( ) {
        // Initialize user (use or create)
        Scanner sc = new Scanner( System.in );
        User user = null;
        String nickname = null;
        boolean success = false;
        while ( !success ) {
            System.out.println( );
            System.out.print( "Please enter your nickname: " );
            nickname = sc.nextLine( );
            
            User_AddResponse userAddResponse = userAddService.addUser( nickname );
            if ( userAddResponse.isSuccess( ) ) {
                user = userAddResponse.getUser( );
                success = true;
            } else
                printErrors( userAddResponse.getErrors( ) );
        }
        
        // Initialize guest room (join or create)
        String roomName = "GuestRoom";
        Room room = null;
        Room_JoinOrCreateResponse roomJoinOrCreateResponse = roomJoinCreateService.init( roomName, user );
        if ( roomJoinOrCreateResponse.isSuccess( ) ) {
            room = roomJoinOrCreateResponse.getRoom( );
        } else {
            printErrors( roomJoinOrCreateResponse.getErrors( ) );
        }
        
        // Check if user is already in that room
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        if ( userFindInRoomResponse.isSuccess( ) )
            userAddToRoomService.add( user, room );
        System.out.println( "\nYou are now chatting in '" + roomName + "'" );
        
        // user and room initialized
        // print help for users
        applicationContext.getBean( PrintAvailableChatCommandsView.class ).execute( );
        
        // ready to get user input
        System.out.println( "\n[info] Entering while(true) loop" ); //debug info, delete in prod
        while ( true ) {
            System.out.println( "\n[info] Ready, waiting for your input" ); //debug info, delete in prod
            
            String input = sc.nextLine( );
            User_InputResponse userInputResponse = userInputService.handle( input );
            
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
                    Message_AddResponse messageAddResponse = messageAddService.addMessage(
                            userInputResponse.getData( ),
                            nickname,
                            room.getId( )
                    );
                    if ( messageAddResponse.isSuccess( ) ) {
                        System.out.println( messageAddResponse.getMessage( ) );
                    } else {
                        printErrors( messageAddResponse.getErrors( ) );
                    }
                    break;
                
                case Constants.JOIN_ROOM:
                    // Это хотелось бы вынести в отдельный View
                    
                    // Validate room name
                    String roomNameFromUserInput = userInputResponse.getData( );
                    List<Error> errors = roomNameValidator.validate( roomNameFromUserInput );
                    if ( errors.isEmpty( ) ) {
                        // Validate and join or create new room
                        roomJoinOrCreateResponse = roomJoinCreateService.init( userInputResponse.getData( ), user );
                        if ( roomJoinOrCreateResponse.isSuccess( ) ) {
                            room = roomJoinOrCreateResponse.getRoom( ); // но нужно как-то вернуть room, от него зависят
                            // другие switch case
                            System.out.println( "User '" + nickname + "' joined room '" + room.getName( ) + "'." );
                        } else {
                            printErrors( roomJoinOrCreateResponse.getErrors( ) );
                        }
                    } else {
                        printErrors( errors );
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
                    Message_GetChatHistoryResponse response = messageGetChatHistoryService.go( room );
                    List<Message> messages = response.getChatHistory( );
                    messages.forEach( System.out::println );
                    break;
                case Constants.LEAVE:
                    List<Room> rooms = userGetAListOfJoinedRoomsService.getList( user );
                    if ( rooms.isEmpty( ) ) {
                        System.out.println( "Cant leave last room" );
                    }
                    
                    break;
            }
        }
    }
    
    private void printErrors( List<Error> errors ) {
        errors.forEach( error -> System.out.println( "Error message = " + error.getErrorMessage( ) ) );
    }
}

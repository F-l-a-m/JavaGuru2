package lv.javaguru.java2.console;

import lv.javaguru.java2.console.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.console.businesslogic.user.User_InputResponse;
import lv.javaguru.java2.console.businesslogic.user.User_InputService;
import lv.javaguru.java2.console.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
class Launcher {
    
    @Autowired private User_InputService userInputService;
    @Autowired private ConsoleSession session;
    
    private ApplicationContext applicationContext;
    
    Launcher( ApplicationContext applicationContext ) {
        this.applicationContext = applicationContext;
    }
    
    protected void go( ) {
        
        init( );
        
        Map<Byte, View> actionMap = new HashMap<>( );
        actionMap.put( Constants.EMPTY_MESSAGE, applicationContext.getBean( EmptyMessageView.class ) );
        actionMap.put( Constants.NORMAL_MESSAGE, applicationContext.getBean( PrintChatMessageView.class ) );
        actionMap.put( Constants.JOIN_ROOM, applicationContext.getBean( JoinChatRoomView.class ) );
        actionMap.put( Constants.BAD_COMMAND, applicationContext.getBean( BadCommandView.class ) );
        actionMap.put( Constants.LIST, applicationContext.getBean( ListAllRoomsView.class ) );
        actionMap.put( Constants.CHANGE_NICKNAME, applicationContext.getBean( ChangeNicknameView.class ) );
        actionMap.put( Constants.PRINT_CHAT_HISTORY, applicationContext.getBean( PrintChatHistoryView.class ) );
        actionMap.put( Constants.LEAVE, applicationContext.getBean( LeaveRoomView.class ) );
        actionMap.put( Constants.REGISTER, applicationContext.getBean( RegistrationView.class ) );
        actionMap.put( Constants.LOGIN, applicationContext.getBean( LoginView.class ) );
        actionMap.put( Constants.QUIT_APP, applicationContext.getBean( ProgramExitView.class ) );
        actionMap.put( Constants.HELP, applicationContext.getBean( PrintAvailableChatCommandsView.class ) );
        
        applicationContext.getBean( PrintAvailableChatCommandsView.class ).execute( );
        
        System.out.println( "\n[info] Entering while(true) loop" ); // Debug info, delete in prod
        Scanner sc = new Scanner( System.in );
        
        while ( true ) {
            System.out.println( "\n[info] Ready, waiting for your input\n" ); // Debug info, delete in prod
            String input = sc.nextLine( );
            User_InputResponse userInputResponse = userInputService.handle( input );
            session.setUserConsoleInputResponse( userInputResponse );
            
            View view = actionMap.get( userInputResponse.getCommand( ) );
            view.execute( );
        }
    }
    
    private void init( ) {
        
        Scanner sc = new Scanner( System.in );
        System.out.println( "1. Register" );
        System.out.println( "2. Login" );
        String choice = sc.nextLine( );
        while ( !isInputValid( choice ) ) {
            System.out.println( "Please choose 1 or 2." );
            choice = sc.nextLine( );
        }
        switch ( choice ) {
            case "1":
                applicationContext.getBean( RegistrationView.class ).execute( );
                break;
            case "2":
                applicationContext.getBean( LoginView.class ).execute( );
                break;
        }
        applicationContext.getBean( JoinDefaultRoomView.class ).execute( );
    }
    
    private boolean isInputValid( String userChoice ) {
        if ( !userChoice.equals( "1" ) && !userChoice.equals( "2" ) ) {
            return false;
        }
        return true;
    }
}

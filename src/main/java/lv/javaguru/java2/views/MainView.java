package lv.javaguru.java2.views;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.Session.ConsoleSession;
import lv.javaguru.java2.businesslogic.user.User_InputResponse;
import lv.javaguru.java2.businesslogic.user.User_InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainView implements View, Constants {
    
    @Autowired private User_InputService userInputService;
    @Autowired private ConsoleSession session;
    
    private ApplicationContext applicationContext;
    
    public MainView( ApplicationContext applicationContext ) {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void execute( ) {
        
        init( );
        
        System.out.println( "\n[info] Entering while(true) loop" ); // Debug info, delete in prod
        while ( true ) {
            System.out.println( "\n[info] Ready, waiting for your input\n" ); // Debug info, delete in prod
            
            Scanner sc = new Scanner( System.in );
            String input = sc.nextLine( );
            User_InputResponse userInputResponse = userInputService.handle( input );
            session.setUserConsoleInputResponse( userInputResponse );
            
            switch ( userInputResponse.getCommand( ) ) {
                case Constants.QUIT_APP:
                    applicationContext.getBean( ProgramExitView.class ).execute( );
                    break;
                
                case Constants.EMPTY_MESSAGE:
                    // [2018/03/26 12:12] username:
                    applicationContext.getBean( EmptyMessageView.class ).execute( );
                    break;
                
                case Constants.NORMAL_MESSAGE:
                    applicationContext.getBean( PrintChatMessageView.class ).execute( );
                    break;
                
                case Constants.JOIN_ROOM:
                    applicationContext.getBean( JoinChatRoomView.class ).execute( );
                    break;
                
                case Constants.BAD_COMMAND:
                    System.out.println( "Command syntax error. Please try again." );
                    break;
                
                case Constants.LIST:
                    applicationContext.getBean( ListAllRoomsView.class ).execute( );
                    break;
                
                case Constants.CHANGE_NICKNAME:
                    applicationContext.getBean( ChangeNicknameView.class ).execute( );
                    break;
                
                case Constants.PRINT_CHAT_HISTORY:
                    applicationContext.getBean( PrintChatHistoryView.class ).execute( );
                    break;
                
                case Constants.LEAVE:
                    applicationContext.getBean( LeaveRoomView.class ).execute( );
                    break;
                
                case Constants.REGISTER:
                    applicationContext.getBean( RegistrationView.class ).execute( );
                    break;
                
                case Constants.LOGIN:
                    applicationContext.getBean( LoginView.class ).execute( );
                    break;
            }
        }
    }
    
    private void init( ) {
        Scanner sc = new Scanner( System.in );
        System.out.println( "1. Register" );
        System.out.println( "2. Login" );
        String choice = sc.nextLine( );
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
}

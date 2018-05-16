package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginRequest;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginResponse;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginView implements View {
    
    @Autowired private User_LoginService loginService;
    @Autowired private ConsoleSession session;
    
    @Override
    public void execute( ) {
        System.out.println( "- - - - - User login - - - - - - - - " );
        System.out.println( "- - - - - - - - - - - - - - - - - - -" );
        
        Scanner sc = new Scanner( System.in );
        boolean loginSuccess = false;
        
        while ( !loginSuccess ) {
            System.out.print( "Login: " );
            String login = sc.nextLine( );
            System.out.print( "Password: " );
            String password = sc.nextLine( );
    
            User_LoginRequest request =
                    new User_LoginRequest( login, password );
            User_LoginResponse response = loginService.login( request );
    
            if ( !response.isSuccess( ) ) {
                printErrors( response.getErrors( ) );
                System.out.println( "Login failed. Please try again..." );
            } else {
                session.setUser( response.getUser( ) );
                System.out.println( "Success you are logged in as " + request.getLogin( ) );
                loginSuccess = true;
            }
        }
    }
}

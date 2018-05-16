package lv.javaguru.java2.console.views;

import lv.javaguru.java2.console.businesslogic.session.ConsoleSession;
import lv.javaguru.java2.console.businesslogic.user.registration.User_RegistrationRequest;
import lv.javaguru.java2.console.businesslogic.user.registration.User_RegistrationResponse;
import lv.javaguru.java2.console.businesslogic.user.registration.User_RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RegistrationView implements View {
    
    @Autowired private User_RegistrationService registrationService;
    @Autowired private ConsoleSession session;
    
    @Override
    public void execute( ) {
        System.out.println( "- - - - - User registration - - - - - " );
        System.out.println( "- - - - - - - - - - - - - - - - - - - " );
        
        Scanner sc = new Scanner( System.in );
        boolean registrationSuccess = false;
        
        while ( !registrationSuccess ) {
            System.out.print( "Login: " );
            String login = sc.nextLine( );
            System.out.print( "Password: " );
            String password = sc.nextLine( );
            System.out.print( "Nickname: " );
            String nickname = sc.nextLine( );
    
            User_RegistrationRequest request =
                    new User_RegistrationRequest( login, password, nickname );
            User_RegistrationResponse response =
                    registrationService.register( request );
    
            if ( !response.isSuccess( ) ) {
                printErrors( response.getErrors( ) );
                System.out.println( "Registration failed. Please try again..." );
            } else {
                session.setUser( response.getUser( ) );
                System.out.println( "Success new user registered!" );
                registrationSuccess = true;
            }
        }
    }
}

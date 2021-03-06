package lv.javaguru.java2.web.mvc;

import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryResponse;
import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryService;
import lv.javaguru.java2.console.businesslogic.room.Room_FindResponse;
import lv.javaguru.java2.console.businesslogic.room.Room_FindService;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginRequest;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginResponse;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginService;
import lv.javaguru.java2.console.businesslogic.user.registration.User_RegistrationRequest;
import lv.javaguru.java2.console.businesslogic.user.registration.User_RegistrationResponse;
import lv.javaguru.java2.console.businesslogic.user.registration.User_RegistrationService;
import lv.javaguru.java2.console.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {
    
    @Autowired private User_LoginService loginService;
    @Autowired private Message_GetChatHistoryService getChatHistoryService;
    @Autowired private Room_FindService roomFindService;
    @Autowired private ChatController chatController;
    @Autowired private User_RegistrationService registrationService;
    
    @RequestMapping(value = "login", method = {RequestMethod.GET})
    public String login( HttpServletRequest request ) {
        return "login";
    }
    
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public ModelAndView loginSubmit( HttpServletRequest request ) {
        // Check credentials
        String login = request.getParameter( "login" );
        String password = request.getParameter( "password" );
        User_LoginRequest loginRequest = new User_LoginRequest( login, password );
        User_LoginResponse loginResponse = loginService.login( loginRequest );
        if ( !loginResponse.isSuccess( ) ) {
            return new ModelAndView( "error", "model", loginResponse.getErrors( ) );
        } else {
            // Create session
            HttpSession session = request.getSession( );
            session.setAttribute( "currentRoom", "GuestRoom" );
            session.setAttribute( "nickname", loginResponse.getUser( ).getNickname( ) );
            // Form model for chat and return it
            return getChatModel( );
        }
    }
    
    @RequestMapping(value = "register", method = {RequestMethod.GET})
    public String register( HttpServletRequest request ) {
        return "register";
    }
    
    @RequestMapping(value = "register", method = {RequestMethod.POST})
    public ModelAndView registerSubmit( HttpServletRequest request ) {
        // Check input values
        String login = request.getParameter( "login" );
        String password = request.getParameter( "password" );
        String nickname = request.getParameter( "nickname" );
        User_RegistrationRequest registrationRequest = new User_RegistrationRequest( login, password, nickname );
        User_RegistrationResponse registrationResponse = registrationService.register( registrationRequest );
        if ( !registrationResponse.isSuccess( ) ) {
            return new ModelAndView( "error", "model", registrationResponse.getErrors( ) );
        } else {
            // Create session
            HttpSession session = request.getSession( );
            session.setAttribute( "currentRoom", "GuestRoom" );
            session.setAttribute( "nickname", nickname );
            // Form model for chat and return it
            return getChatModel( );
        }
    }
    
    @RequestMapping(value = "logout", method = {RequestMethod.GET})
    public String logOut( HttpServletRequest request ) {
        HttpSession session = request.getSession( );
        session.invalidate( );
        return "logout";
    }
    
    private ModelAndView getChatModel( ) { // Add room parameter later
        Room_FindResponse findResponse = roomFindService.find( "GuestRoom" );
        if ( findResponse.isSuccess( ) ) {
            Room room = findResponse.getRoom( );
            Message_GetChatHistoryResponse response = getChatHistoryService.getChatHistoryForRoom( room );
            return new ModelAndView( "chat-v2", "model", response.getChatHistory( ) );
        }
        return new ModelAndView( "error", "model", findResponse.getErrors( ) );
    }
}

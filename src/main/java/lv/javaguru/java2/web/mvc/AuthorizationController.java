package lv.javaguru.java2.web.mvc;

import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryResponse;
import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryService;
import lv.javaguru.java2.console.businesslogic.room.Room_FindResponse;
import lv.javaguru.java2.console.businesslogic.room.Room_FindService;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginRequest;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginResponse;
import lv.javaguru.java2.console.businesslogic.user.login.User_LoginService;
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
    
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String login( HttpServletRequest request ) {
        return "login";
    }
    
    @RequestMapping(value = "/", method = {RequestMethod.POST})
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
            Room_FindResponse findResponse = roomFindService.find( "GuestRoom" );
            if ( findResponse.isSuccess( ) ) {
                Room room = findResponse.getRoom( );
                Message_GetChatHistoryResponse response = getChatHistoryService.getChatHistoryForRoom( room );
                if ( response.getChatHistory( ).isEmpty( ) ) {
                    return new ModelAndView( "error", "model", "no messages in room" );
                } else {
                    return new ModelAndView( "chat", "model", response.getChatHistory( ) );
                }
            }
            return new ModelAndView( "error", "model", "room not found" );
        }
    }
    
    @RequestMapping(value = "logout", method = {RequestMethod.GET})
    public String logOut( HttpServletRequest request ) {
        HttpSession session = request.getSession( );
        session.invalidate( );
        
        return "logout";
    }
}

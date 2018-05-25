package lv.javaguru.java2.web.mvc;

import lv.javaguru.java2.console.businesslogic.message.Message_AddResponse;
import lv.javaguru.java2.console.businesslogic.message.Message_AddService;
import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryResponse;
import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryService;
import lv.javaguru.java2.console.businesslogic.room.Room_FindResponse;
import lv.javaguru.java2.console.businesslogic.room.Room_FindService;
import lv.javaguru.java2.console.domain.Message;
import lv.javaguru.java2.console.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "chat")
public class ChatController {
    
    @Autowired private Message_GetChatHistoryService getChatHistoryService;
    @Autowired private Room_FindService roomFindService;
    @Autowired private Message_AddService messageAddService;
    
    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView showChat( HttpServletRequest request ) {
        Room_FindResponse findResponse = roomFindService.find( "GuestRoom" );
        if ( findResponse.isSuccess( ) ) {
            Room room = findResponse.getRoom( );
            Message_GetChatHistoryResponse response = getChatHistoryService.getChatHistoryForRoom( room );
            return new ModelAndView( "chat-v2", "model", response.getChatHistory( ) );
        }
        return new ModelAndView( "error", "model", findResponse.getErrors() );
    }
    
    @RequestMapping(method = {RequestMethod.POST})
    public ModelAndView saveUserInput( HttpServletRequest request ) {
        HttpSession session = request.getSession( );
        String currentRoom = (String) session.getAttribute( "currentRoom" );
        String nickname = (String) session.getAttribute( "nickname" );
        if ( currentRoom == null && nickname == null ) {
            currentRoom = "GuestRoom";
            nickname = "Anonymous";
        }
        // Get room object
        Room_FindResponse roomFindResponse = roomFindService.find( currentRoom );
        if ( roomFindResponse.isSuccess( ) ) {
            Room room = roomFindResponse.getRoom( );
            // Save new message to db
            String message = request.getParameter( "userInput" );
            Message_AddResponse messageAddResponse =
                    messageAddService.addMessage( message, nickname, room );
            // Get full chat history for current room and forward it to jsp
            Message_GetChatHistoryResponse chatHistoryResponse = getChatHistoryService.getChatHistoryForRoom( room );
            return new ModelAndView( "chat-v2", "model", chatHistoryResponse.getChatHistory( ) );
        }
        return new ModelAndView( "error", "model", roomFindResponse.getErrors() );
    }
}

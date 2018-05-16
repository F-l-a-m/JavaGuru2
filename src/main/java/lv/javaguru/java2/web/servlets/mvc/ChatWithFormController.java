package lv.javaguru.java2.web.servlets.mvc;

import lv.javaguru.java2.console.businesslogic.message.Message_AddResponse;
import lv.javaguru.java2.console.businesslogic.message.Message_AddService;
import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryResponse;
import lv.javaguru.java2.console.businesslogic.message.getChatHistory.Message_GetChatHistoryService;
import lv.javaguru.java2.console.businesslogic.room.Room_FindResponse;
import lv.javaguru.java2.console.businesslogic.room.Room_FindService;
import lv.javaguru.java2.console.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "chat")
public class ChatWithFormController {
    
    @Autowired private Message_GetChatHistoryService getChatHistoryService;
    @Autowired private Room_FindService roomFindService;
    @Autowired private Message_AddService messageAddService;
    
    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView showChat( HttpServletRequest request ) {
        ChatInput chatInput = new ChatInput();
        
        Room_FindResponse findResponse = roomFindService.find( "GuestRoom" );
        if ( findResponse.isSuccess( ) ) {
            Room room = findResponse.getRoom( );
            Message_GetChatHistoryResponse response = getChatHistoryService.getChatHistoryForRoom( room );
            if ( response.getChatHistory( ).isEmpty( ) ) {
                return new ModelAndView( "error", "model", "no messages in room" );
            } else {
                return new ModelAndView( "chatWithForm", "model", response.getChatHistory( ) );
            }
        }
        return new ModelAndView( "error", "model", "room not found" );
    }
    
    @RequestMapping(method = {RequestMethod.POST})
    public ModelAndView saveUserInput( HttpServletRequest request, @ModelAttribute("userForm") ChatInput chatInput ) {
        
        
        Room_FindResponse findResponse = roomFindService.find( "GuestRoom" );
        if ( findResponse.isSuccess( ) ) {
            Room room = findResponse.getRoom( );
            
            // save new message to db
            Message_AddResponse messageAddResponse =
                    messageAddService.addMessage( chatInput.getMessage( ), "nickname", room );
            
            Message_GetChatHistoryResponse chatHistoryResponse = getChatHistoryService.getChatHistoryForRoom( room );
            if ( chatHistoryResponse.getChatHistory( ).isEmpty( ) ) {
                return new ModelAndView( "error", "model", "no messages in room" );
            } else {
                return new ModelAndView( "chatWithForm", "model", chatHistoryResponse.getChatHistory( ) );
            }
        }
        return new ModelAndView( "error", "model", "room not found" );
    }
}

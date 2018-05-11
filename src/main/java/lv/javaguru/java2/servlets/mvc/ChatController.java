package lv.javaguru.java2.servlets.mvc;

import lv.javaguru.java2.businesslogic.message.getChatHistory.Message_GetChatHistoryResponse;
import lv.javaguru.java2.businesslogic.message.getChatHistory.Message_GetChatHistoryService;
import lv.javaguru.java2.businesslogic.room.Room_FindResponse;
import lv.javaguru.java2.businesslogic.room.Room_FindService;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChatController {
    
    @Autowired private Message_GetChatHistoryService getChatHistoryService;
    @Autowired private Room_FindService roomFindService;
    
    @RequestMapping(value = "chat", method = {RequestMethod.GET})
    public ModelAndView getChatHistory( HttpServletRequest request ) {
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

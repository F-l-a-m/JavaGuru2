package lv.javaguru.java2.views;

import lv.javaguru.java2.domain.Room;

//@Component
public class PrintChatHistoryInRoomView implements View {
    
    private Room room;
    //@Autowired private Message_GetChatHistoryService getAllChatHistoryService;
    
    public PrintChatHistoryInRoomView( Room room ) {
        this.room = room;
    }
    
    @Override
    public void execute( ) {
        // Clear console
        System.out.println( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" );
        
        // Print whole message history in given chat room
        //Message_GetChatHistoryResponse response = getAllChatHistoryService.go( room );
        //List<Message> messages = response.getChatHistory( );
        //messages.forEach( System.out::println );
    }
}

package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.Room_AddService;
import lv.javaguru.java2.businesslogic.room.Room_FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinChatRoomView implements View {
    
    @Autowired private Room_FindService roomFindService;
    @Autowired private Room_AddService roomAddService;
    
    @Override
    public void execute( ) {
    
    }
}

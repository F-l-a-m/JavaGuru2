package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.room.AddRoomService;
import lv.javaguru.java2.businesslogic.room.FindRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinChatRoomView implements View {
    
    @Autowired private FindRoomService findRoomService;
    @Autowired private AddRoomService addRoomService;
    
    @Override
    public void execute( ) {
    
    }
}

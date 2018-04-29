package lv.javaguru.java2.businesslogic.Session;

import lv.javaguru.java2.businesslogic.user.User_InputResponse;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

@Component
class ConsoleSessionImpl implements ConsoleSession {
    
    private User user;
    private Room room;
    private User_InputResponse userConsoleInputResponse;
    
    @Override
    public User getUser( ) {
        return user;
    }
    
    @Override
    public void setUser( User user ) {
        this.user = user;
    }
    
    @Override
    public Room getRoom( ) {
        return room;
    }
    
    @Override
    public void setRoom( Room room ) {
        this.room = room;
    }
    
    @Override
    public User_InputResponse getUserConsoleInputResponse( ) {
        return userConsoleInputResponse;
    }
    
    @Override
    public void setUserConsoleInputResponse( User_InputResponse response ) {
        this.userConsoleInputResponse = response;
    }
}

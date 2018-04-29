package lv.javaguru.java2.businesslogic.Session;

import lv.javaguru.java2.businesslogic.user.User_InputResponse;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;

public interface ConsoleSession {
    
    User getUser( );
    
    void setUser( User user );
    
    Room getRoom( );
    
    void setRoom( Room room );
    
    User_InputResponse getUserConsoleInputResponse( );
    
    void setUserConsoleInputResponse( User_InputResponse response );
}

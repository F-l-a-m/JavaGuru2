package lv.javaguru.java2.console.businesslogic.session;

import lv.javaguru.java2.console.businesslogic.user.User_InputResponse;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;

public interface ConsoleSession {
    
    User getUser( );
    
    void setUser( User user );
    
    Room getRoom( );
    
    void setRoom( Room room );
    
    User_InputResponse getUserConsoleInputResponse( );
    
    void setUserConsoleInputResponse( User_InputResponse response );
}

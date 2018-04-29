package lv.javaguru.java2.businesslogic.Session;

import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;

public interface ConsoleSession {
    
    User getUser( );
    
    User setUser( User user );
    
    Room getRoom( );
    
    Room setRoom( Room room );
}

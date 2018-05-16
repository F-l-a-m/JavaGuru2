package lv.javaguru.java2.console.database;

import lv.javaguru.java2.console.domain.Message;
import lv.javaguru.java2.console.domain.Room;

import java.util.List;

public interface MessageRepository {
    
    void save( Message message );
    
    List getAllMessages( Room room );
    
}

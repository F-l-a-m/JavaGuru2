package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.Room;

import java.util.List;

public interface MessageRepository {
    
    void save( Message message );
    
    List getAllMessages( Room room );
    
}

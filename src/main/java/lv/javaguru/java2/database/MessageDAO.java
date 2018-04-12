package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Message;

import java.util.List;

public interface MessageDAO {
    
    Message add( String message, String nickname, Long roomId );
    
    List getAllMessages( Long roomId );
    
}

package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.models.ChatLine;
import java.util.List;

public interface ChatDatabase {

    void add(ChatLine chatLine);

    //Optional<ChatLine> findLast(); ?

    void remove(ChatLine chatLine);

    List<ChatLine> getAllChat();

}

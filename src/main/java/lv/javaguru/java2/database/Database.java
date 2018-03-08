package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.chat.ChatLine;
import lv.javaguru.java2.businesslogic.user.User;

import java.util.List;

public interface Database {

    void addChatLine(ChatLine chatLine);

    List<ChatLine> getAllChat();

    ChatLine getLastChatMessage();

    User getCurrentUser();

    void setCurrentUser(User user);
}

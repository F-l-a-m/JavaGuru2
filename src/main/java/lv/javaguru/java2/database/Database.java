package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.models.ChatLine;
import lv.javaguru.java2.businesslogic.models.User;

import java.util.List;

public interface Database {

    void addChatLine(ChatLine chatLine);

    List<ChatLine> getAllChat();

    ChatLine getLastChatMessage();

    void createNewUser(User user);

    User getCurrentUser();

    void changeUserNickname(User user);

}

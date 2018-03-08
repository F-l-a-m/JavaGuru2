package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.chat.ChatLine;
import lv.javaguru.java2.businesslogic.user.User;
import java.util.List;
import java.util.ArrayList;

public class InMemoryDatabase implements Database {

    private List<ChatLine> chatHistory = new ArrayList<>();
    //private List<User> userTable = new ArrayList<>();
    private User currentUser;

    @Override
    public void addChatLine(ChatLine chatLine) {
        chatHistory.add(chatLine);
    }

    @Override
    public List<ChatLine> getAllChat() {
        List<ChatLine> copyChat = new ArrayList<>();
        copyChat.addAll(chatHistory);
        return copyChat;
    }

    @Override
    public ChatLine getLastChatMessage(){
        return chatHistory.get(chatHistory.size() - 1);
    }

    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}

package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.models.ChatLine;
import lv.javaguru.java2.businesslogic.models.User;

import java.util.List;
import java.util.ArrayList;

public class InMemoryDatabase implements Database {

    private List<ChatLine> chatHistory = new ArrayList<>();
    private List<User> userTable = new ArrayList<>();

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
    public void createNewUser(User user){
        userTable.add(user);
    }

    @Override
    public User getCurrentUser() {
        return userTable.get(0);
    }

    @Override
    public void changeUserNickname(User user) {
        userTable.add(user);
    }
}

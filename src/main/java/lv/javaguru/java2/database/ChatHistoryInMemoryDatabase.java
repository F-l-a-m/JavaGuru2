package lv.javaguru.java2.database;

import lv.javaguru.java2.businesslogic.models.ChatLine;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class ChatHistoryInMemoryDatabase implements ChatDatabase{

    private List<ChatLine> chatHistory = new ArrayList<>();

    @Override
    public void add(ChatLine chatLine) {
        chatHistory.add(chatLine);
    }

    @Override
    public void remove(ChatLine chatLine) {

    }

    @Override
    public List<ChatLine> getAllChat() {
        List<ChatLine> copyChat = new ArrayList<>();
        copyChat.addAll(chatHistory);
        return copyChat;
    }

    @Override
    public ChatLine GetLastMessage(){
        return chatHistory.get(chatHistory.size() - 1);
    }
}

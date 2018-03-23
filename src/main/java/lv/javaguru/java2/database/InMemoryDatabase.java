/*
package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.Message;
import lv.javaguru.java2.domain.ChatRoom;
import lv.javaguru.java2.domain.User;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class InMemoryDatabase implements Database {

    private List<Message> chatHistory = new ArrayList<>();
    // all currently available chat rooms
    private List<ChatRoom> roomList = new ArrayList();
    // all registered users
    private List<User> registeredUserList = new ArrayList<>();
    // all connected users
    private List<User> connectedUserList = new ArrayList<>();
    private User currentUser;

    @Override
    public void addChatLine(Message message) {
        chatHistory.add(message);
    }

    @Override
    public List<Message> getAllChat() {
        List<Message> copyChat = new ArrayList<>();
        copyChat.addAll(chatHistory);
        return copyChat;
    }

    @Override
    public Message getLastChatMessage(){
        return chatHistory.get(chatHistory.size() - 1);
    }

    @Override
    public Optional<User> getLastUser() {
        return this.currentUser;
    }

    @Override
    public void addNewUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void addToRoomList(ChatRoom newRoom){
        roomList.add(newRoom);
    }

    @Override
    public void removeFormRoomList(ChatRoom roomToDelete){
        roomList.remove(roomToDelete);
    }

    @Override
    public List<ChatRoom> getRoomList(){
        List<ChatRoom> copyRooms = new ArrayList<>();
        copyRooms.addAll(roomList);
        return copyRooms;
    }

    @Override
    public Optional<ChatRoom> findChatRoom(String name){
        return roomList.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst();
    }
}
*/

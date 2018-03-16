/*
package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.Response;
import lv.javaguru.java2.businesslogic.chat.StringCache;
import lv.javaguru.java2.businesslogic.room.ChatRoomService;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

public class JoinChatRoomView implements View{

    private Database database;
    private ChatRoomService chatRoomService;
    private StringCache stringCache;

    public JoinChatRoomView(Database database, StringCache stringCache){
        this.database = database;
        this.stringCache = stringCache;
        this.chatRoomService = new ChatRoomService();
    }

    @Override
    public void execute(){
        // If room exists - join
        // db -get room list
        String roomname = stringCache.getTemporaryString();
        User currentUser = database.getCurrentUser();
        Response response = chatRoomService.join(database, roomname, currentUser);
        if (response.isSuccess()){
            System.out.println("Successfully joined " + roomname);
            System.out.println("Now chatting in " + roomname);

            // now chatting in roomname ...
            // print users in room
        }



        // If room does not exist - create new
    }
}
*/

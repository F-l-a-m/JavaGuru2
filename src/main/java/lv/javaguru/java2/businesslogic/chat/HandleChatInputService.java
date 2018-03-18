/*
package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.database.Database;

public class HandleChatInputService implements Constants {

    private Database database;
    private SaveChatMessageService saveChatMessageService;
    private StringCache stringCache;
    private int maxCommandLength;
    private String input;

    public HandleChatInputService(Database database, StringCache stringCache) {
        this.database = database;
        saveChatMessageService = new SaveChatMessageService(database); // how to test ?
        this.stringCache = stringCache;
        maxCommandLength = 40;
    }

    public Enum handle(String userInput){
        input = userInput;
        // Empty message
        if(input.isEmpty() || userInput.trim().isEmpty())
            return userActions.EMPTY_MESSAGE;
        // Check if user entered a command and handle it
        else if(input.charAt(0) == '/')
           return handleChatCommand();
        // Handle as usual message
        else return handleMessage();
    }

    private Enum handleChatCommand(){
        if(input.length() > maxCommandLength)
            return userActions.BAD_COMMAND;
        else {
            input = input.trim();
            String[] splitStr = input.split("\\s+");

            // One word commands
            if(splitStr.length == 1) {
                switch (splitStr[0]) {
                    case "/quit":
                        return userActions.QUIT;
                    case "/r":
                        return userActions.REFRESH_CONSOLE;
                }
            }

            // Two word commands
            else if (splitStr.length == 2){
                switch (splitStr[0]){
                    case "/nick":
                        // write nick to db? temp variable ?
                        // need later in view
                        stringCache.setTemporaryString(splitStr[1]);
                        return userActions.CHANGE_NICK;
                    case "/join":
                        stringCache.setTemporaryString(splitStr[1]);
                        return userActions.JOIN_CHAT_ROOM;
                }
            }
        }
        return userActions.BAD_COMMAND;
    }

    */
/*private Enum handleMessage(){
        saveChatMessageService.SaveMessageToDatabase(
                new Message(new Timestamp().getTimestamp(),
                        database.getUserById().getNickname(),
                        input)
        );
        return userActions.PRINT_MESSAGE;
    }*//*

}
*/

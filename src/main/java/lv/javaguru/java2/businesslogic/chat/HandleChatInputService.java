package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.room.ChatRoom;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

public class HandleChatInputService implements Constants {

    private Database database;
    private StringCache stringCache;
    private int maxCommandLength;
    private String input;
    private User user;

    public HandleChatInputService(Database database, User user, StringCache stringCache) {
        this.database = database;
        this.stringCache = stringCache;
        this.user = user;
        maxCommandLength = 40;
    }

    public Enum handle(String userInput) {
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

    private Enum handleChatCommand() {
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
            else if (splitStr.length == 2) {
                switch (splitStr[0]) {
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

private Enum handleMessage() {
        MessageService messageService = new MessageService(database);
        messageService.saveMessageToDatabase(input, user, CurrentRoom.getRoom());
        return userActions.PRINT_MESSAGE;
    }
}

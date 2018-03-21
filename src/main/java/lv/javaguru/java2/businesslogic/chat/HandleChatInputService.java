package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.StringCache;
import lv.javaguru.java2.businesslogic.room.CurrentRoom;
import lv.javaguru.java2.businesslogic.user.User;
import lv.javaguru.java2.database.Database;

public class HandleChatInputService implements Constants {

    private final Database database;
    private final StringCache stringCache;
    private final int maxCommandLength;
    private String input;
    private final User user;
    private final MessageService messageService;

    public HandleChatInputService(Database database, User user, StringCache stringCache, MessageService messageService) {
        this.database = database;
        this.stringCache = stringCache;
        this.user = user;
        this.messageService = messageService;
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
        else {
            handleInputAsMessage();
            return userActions.PRINT_MESSAGE;
        }
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
                    case "/list":
                        return userActions.LIST_CHAT_ROOMS;
                    case "/leave":
                        return userActions.LEAVE_CHAT_ROOM;
                }
            }

            // Two word commands
            else if (splitStr.length == 2) {
                switch (splitStr[0]) {
                    case "/nick":
                        stringCache.setTemporaryString(splitStr[1]); // need later in view
                        return userActions.CHANGE_NICK;
                    case "/join":
                        stringCache.setTemporaryString(splitStr[1]);
                        return userActions.JOIN_CHAT_ROOM;
                }
            }
        }
        return userActions.BAD_COMMAND;
    }

private void handleInputAsMessage() {
        messageService.saveMessageToDatabase(input, user, CurrentRoom.getRoom());
    }
}

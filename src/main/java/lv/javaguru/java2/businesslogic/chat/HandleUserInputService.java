package lv.javaguru.java2.businesslogic.chat;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.database.Database;

public class HandleUserInputService implements Constants {

    private UserService userService;
    private SaveChatMessageService saveChatMessageService;
    private LastUserInput lastUserInput;
    private int maxCommandLength;

    public HandleUserInputService(Database database, LastUserInput lastUserInput) {
        this.userService = new UserService(database);
        this.saveChatMessageService = new SaveChatMessageService(database);
        this.lastUserInput = lastUserInput;
        this.maxCommandLength = 40;
    }

    public Enum handle(String userInput){

        // Empty message
        if(userInput.isEmpty()){
            return userActions.EMPTY_MESSAGE;
        }

        // Check if user entered a command and handle it
        else if(userInput.charAt(0) == '/'){
           return handleChatCommand(userInput);
        }

        // Handle as usual message
        else {
            ChatLine newLine = new ChatLine(
                    new Timestamp().getTimestamp(),
                    userService.getCurrentUser().getNickname(),
                    userInput
            );
            saveChatMessageService.SaveMessageToDatabase(newLine);
            return userActions.PRINT_MESSAGE;
        }
    }

    private Enum handleChatCommand(String userInput){
        if(userInput.length() > maxCommandLength){
            return userActions.BAD_COMMAND;
        }
        else {
            userInput = userInput.trim();
            String[] splitStr = userInput.split("\\s+");

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
                        lastUserInput.setUserInput(splitStr[1]);
                        return userActions.CHANGE_NICK;
                }
            }
        }
        return userActions.BAD_COMMAND;
    }
}

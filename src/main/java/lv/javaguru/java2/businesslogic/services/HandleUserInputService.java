package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.models.ChatLine;
import lv.javaguru.java2.businesslogic.models.Timestamp;
import lv.javaguru.java2.database.Database;

public class HandleUserInputService {

    private Database database;
    private SaveChatMessageService saveChatMessageService;

    public HandleUserInputService(Database database) {
        this.database = database;
        this.saveChatMessageService = new SaveChatMessageService(database);
    }

    public Enum HandleUserInput(String userInput){
        // Empty message
        if(userInput.equals("")){
            return Constants.userActions.EMPTY_MESSAGE;
        }
        // Check if user entered a command and handle it
        else if(userInput.charAt(0) == '/'){
            switch (userInput){
                case "/quit":
                    return Constants.userActions.QUIT;
                case "/nick":
                    return Constants.userActions.CHANGE_NICK;
                case "/r":
                    return Constants.userActions.REFRESH_CONSOLE;
                default:
                    return Constants.userActions.BAD_COMMAND;
            }
        }
        // Handle as usual message
        else {
            ChatLine newLine = new ChatLine(
                    new Timestamp().getTimestamp(),
                    database.getCurrentUser().getNickname(),
                    userInput
            );
            saveChatMessageService.SaveMessageToDatabase(newLine);

            switch (userInput){
                default:
                    return Constants.userActions.MESSAGE;
            }
        }
    }
}

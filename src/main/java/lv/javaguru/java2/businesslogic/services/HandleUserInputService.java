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

    public Enum CheckLine(String input){
        // Empty message
        if(input.equals("")){
            return Constants.userActions.EMPTY_MESSAGE;
        }
        // Check if user entered a command and handle it
        else if(input.charAt(0) == '/'){
            switch (input){
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
                    input
            );
            saveChatMessageService.SaveMessageToDatabase(newLine);

            switch (input){
                default:
                    return Constants.userActions.MESSAGE;
            }
        }
    }
}

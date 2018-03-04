package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.models.ChatLine;
import lv.javaguru.java2.Globals;
import lv.javaguru.java2.businesslogic.models.Timestamp;
import lv.javaguru.java2.database.ChatDatabase;

public class HandleUserInputService {

    private ChatDatabase database;
    private SaveChatMessageService saveChatMessageService;

    public HandleUserInputService(ChatDatabase database) {
        this.database = database;
        this.saveChatMessageService = new SaveChatMessageService(database);
    }

    public Enum CheckLine(String input){
        // Check if user entered a command and handle it
        if(input.charAt(0) == '/'){
            switch (input){
                case "/quit":
                    System.exit(0);
                    return Constants.userActions.QUIT;
                case "/nick":
                    return Constants.userActions.CHANGE_NICK;
                case "/r":
                    return Constants.userActions.REFRESH_CONSOLE;
                default:
                    return Constants.userActions.BAD_COMMAND;
            }
        }
        // Handle as message
        else {
            ChatLine newLine = new ChatLine(
                    new Timestamp().getTimestamp(),
                    Globals.getUser().getNickname(),
                    input
            );
            saveChatMessageService.SaveMessageToDatabase(newLine);

            switch (input){
                case "":
                    return Constants.userActions.EMPTY_MESSAGE;
                default:
                    return Constants.userActions.MESSAGE;
            }
        }
    }
}

/*
package lv.javaguru.java2.businesslogic.message;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.user.UserService;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandleChatInputService implements Constants {
    
    @Autowired private UserService userService;
    private String input;
    private final int maxCommandLength = 40;

    public Enum handle(User user, String userInput) {
        input = userInput;
        // Empty message
        if(input.isEmpty() || userInput.trim().isEmpty())
            return userActions.EMPTY_MESSAGE;
        // Check if user entered a command and handle it
        else if(input.charAt(0) == '/')
           return handleChatCommand(user);
        // Handle as usual message
        else {
            userService.setUserInput(user, userInput);
            return userActions.PRINT_MESSAGE;
        }
    }

    private Enum handleChatCommand(User user) {
        if(input.length() > maxCommandLength)
            return userActions.BAD_COMMAND;
        else {
            input = input.trim();
            String[] splitStr = input.split("\\s+");
            String command = splitStr[0];

            // One word commands
            if(splitStr.length == 1) {
                switch (command) {
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
                String userData = splitStr[1];
                switch (command) {
                    case "/nick":
                        userService.setUserInput(user, userData);
                        return userActions.CHANGE_NICK;
                    case "/join":
                        userService.setUserInput(user, userData);
                        return userActions.JOIN_CHAT_ROOM;
                }
            }
        }
        return userActions.BAD_COMMAND;
    }
}
*/

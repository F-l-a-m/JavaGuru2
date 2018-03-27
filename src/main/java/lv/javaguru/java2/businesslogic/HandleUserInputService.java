package lv.javaguru.java2.businesslogic;

import lv.javaguru.java2.Constants;
import org.springframework.stereotype.Component;

@Component
public class HandleUserInputService implements Constants {
    
    private String input;
    private final int maxCommandLength = 40;
    
    public UserInputResponse handle( String userInput ) {
        input = userInput;
        
        // Empty message
        if (input.isEmpty( ) || userInput.trim( ).isEmpty( ))
            return new UserInputResponse( Constants.EMPTY_MESSAGE, null );
            
        // Check if user entered a command and handle it
        else if (input.charAt( 0 ) == '/') {
            if (input.length( ) > maxCommandLength)
                return new UserInputResponse( Constants.BAD_COMMAND, null );
            input = input.trim( );
            String[] splitStr = input.split( "\\s+" );
    
            byte result = 0;
            if (splitStr.length == 1) {
                result = oneWordCommand( splitStr[0] );
                return new UserInputResponse( result,  null);
            }
            else if (splitStr.length == 2) {
                result = twoWordCommand( splitStr[0] );
                return new UserInputResponse( result, splitStr[1] );
            }
        } else {
            // Handle as usual message
            return new UserInputResponse( Constants.NORMAL_MESSAGE, input );
        }
        return new UserInputResponse( Constants.BAD_COMMAND, null );
    }
    
    private byte oneWordCommand( String command ) {
        // One word commands
        switch (command) {
            case "/quit":   return Constants.QUIT_APP;
            case "/r":      break;
            case "/list":   return Constants.LIST;
            case "/leave":  break;
        }
        return Constants.BAD_COMMAND;
    }
    
    private byte twoWordCommand( String command ) {
        // Two word commands
        switch (command) {
            case "/nick":   break;
            case "/join":   return Constants.JOIN_ROOM;
        }
         return Constants.BAD_COMMAND;
    }
}

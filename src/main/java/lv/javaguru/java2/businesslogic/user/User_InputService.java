package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.Constants;
import org.springframework.stereotype.Component;

@Component
public class User_InputService implements Constants {
    
    public User_InputResponse handle( String userInput ) {
        String input = userInput;
        
        // Empty message
        if ( input.isEmpty( ) || userInput.trim( ).isEmpty( ) )
            return new User_InputResponse( Constants.EMPTY_MESSAGE, null );
            
            // Check if user entered a command and handle it
        else if ( input.charAt( 0 ) == '/' ) {
            final int maxCommandLength = 40;
            if ( input.length( ) > maxCommandLength )
                return new User_InputResponse( Constants.BAD_COMMAND, null );
            input = input.trim( );
            String[] splitStr = input.split( "\\s+" );
            
            byte result;
            if ( splitStr.length == 1 ) {
                result = oneWordCommand( splitStr[0] );
                return new User_InputResponse( result, null );
            } else if ( splitStr.length == 2 ) {
                result = twoWordCommand( splitStr[0] );
                return new User_InputResponse( result, splitStr[1] );
            }
        } else {
            // Handle as usual message
            return new User_InputResponse( Constants.NORMAL_MESSAGE, input );
        }
        return new User_InputResponse( Constants.BAD_COMMAND, null );
    }
    
    private byte oneWordCommand( String command ) {
        // One word commands
        switch ( command ) {
            case "/quit":   return Constants.QUIT_APP;
            case "/r":      return Constants.GET_CHAT_HISTORY;
            case "/list":   return Constants.LIST;
            case "/leave":  return Constants.LEAVE;
        }
        return Constants.BAD_COMMAND;
    }
    
    private byte twoWordCommand( String command ) {
        // Two word commands
        switch ( command ) {
            case "/nick":   break;
            case "/join":   return Constants.JOIN_ROOM;
        }
        return Constants.BAD_COMMAND;
    }
}

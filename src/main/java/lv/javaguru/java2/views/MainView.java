package lv.javaguru.java2.views;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.UserInputResponse;
import lv.javaguru.java2.businesslogic.HandleUserInputService;
import lv.javaguru.java2.businesslogic.message.*;
import lv.javaguru.java2.businesslogic.room.*;
import lv.javaguru.java2.businesslogic.user.*;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainView implements View, Constants {
    
    @Autowired private AddUserService addUserService;
    @Autowired private AddRoomService addRoomService;
    @Autowired private AddMessageService addMessageService;
    @Autowired private AddUserToRoomService addUserToRoomService;
    @Autowired private FindUserService findUserService;
    @Autowired private FindRoomService findRoomService;
    @Autowired private FindUserInRoomService findUserInRoomService;
    @Autowired private HandleUserInputService handleUserInputService;
    @Autowired private InitializeUserService initializeUserService;
    @Autowired private InitializeRoomService initializeRoomService;
    
    @Override
    public void execute( ) {
        System.out.println( );
        System.out.print( "Please enter your nickname: " );
        Scanner sc = new Scanner( System.in );
        String nickname = sc.nextLine( );
        System.out.print( "Please enter room name: " );
        String roomName = sc.nextLine( );
        
        
        InitializeUserResponse initializeUserResponse = initializeUserService.init( nickname );
        if ( initializeUserResponse.isSuccess( ) ) {
            User user = initializeUserResponse.getUser( );
            
            
            InitializeRoomResponse initializeRoomResponse = initializeRoomService.init( roomName, nickname );
            if ( initializeRoomResponse.isSuccess( ) ) {
                Room room = initializeRoomResponse.getRoom( );
                
                // check if user is already in that room
                boolean isUserAlreadyInThatRoom = findUserInRoomService.findUserInRoom( user.getId( ), roomName );
                if ( !isUserAlreadyInThatRoom )
                    addUserToRoomService.add( user, room );
                
                // user and room initialized
                // ready to get user input
                System.out.println( "[info] Entering while(true) loop" ); //debug info, delete in prod
                while ( true ) {
                    System.out.println( "[info] Ready, waiting for your input" ); //debug info, delete in prod
                    String input = sc.nextLine( );
                    UserInputResponse userInputResponse = handleUserInputService.handle( input );
                    switch ( userInputResponse.getCommand( ) ) {
                        case Constants.QUIT_APP:
                            ProgramExitView programExitView = new ProgramExitView( );
                            programExitView.execute( );
                            break;
                        case Constants.EMPTY_MESSAGE:
                            // [2018/03/26 12:12] username:
                            System.out.println( MyTimestamp.getStringTimestamp( )
                                    + user.getNickname( ) + ':' );
                            break;
                        case Constants.NORMAL_MESSAGE:
                            AddMessageResponse addMessageResponse = addMessageService.addMessage(
                                    userInputResponse.getData( ),
                                    nickname,
                                    room.getId( )
                            );
                            if ( addMessageResponse.isSuccess( ) ) {
                                System.out.println( MyTimestamp.getStringTimestamp( )
                                        + user.getNickname( ) + ':'
                                        + userInputResponse.getData( )
                                );
                            } else
                                printErrors( addMessageResponse.getErrors( ) );
                            break;
                        case Constants.JOIN_ROOM:
                            //
                            break;
                    }
                }
            } else
                printErrors( initializeRoomResponse.getErrors( ) );
        } else
            printErrors( initializeUserResponse.getErrors( ) );
        System.out.println( );
    }
    
    private void printErrors( List<Error> errors ) {
        errors.forEach( error -> {
            System.out.println( "Error message = " + error.getErrorMessage( ) );
        } );
    }
}

package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.AddUserToRoomService;
import lv.javaguru.java2.businesslogic.user.FindUserInRoomService;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class JoinCreateRoomService {
    
    @Autowired private FindRoomService findRoomService;
    @Autowired private AddRoomService addRoomService;
    @Autowired private RoomNameValidator validator;
    @Autowired private FindUserInRoomService findUserInRoomService;
    @Autowired private Database database;
    @Autowired private AddUserToRoomService addUserToRoomService;
    
    @Transactional
    public JoinCreateRoomResponse init( String roomName, User user ) {
        
        List<Error> errors = new ArrayList<>( );
    
        Room room = null;
        // Check if room exists
        FindRoomResponse findRoomResponse = findRoomService.findRoomByName( roomName );
        if ( findRoomResponse.isSuccess( ) ) {
            // use found room
            room = findRoomResponse.getRoom();
        } else {
            // add new room
            AddRoomResponse addRoomResponse = addRoomService.addRoom( roomName, user.getNickname( ) );
            if ( addRoomResponse.isSuccess( ) ) {
                room = addRoomResponse.getRoom();
            } else {
                errors.add( new Error( "Failed to create new room" ) );
                return new JoinCreateRoomResponse( null, errors, false );
            }
        }
        
        // Check if user is already in that room
        boolean isUserAlreadyInThatRoom = findUserInRoomService.findUserInRoom( user.getId( ), room.getId() );
        if ( !isUserAlreadyInThatRoom ) {
            addUserToRoomService.add( user, room );
        }
        return new JoinCreateRoomResponse( room, errors, true );
    }
}

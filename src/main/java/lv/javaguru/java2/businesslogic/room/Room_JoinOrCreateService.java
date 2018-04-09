package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.User_AddToRoomService;
import lv.javaguru.java2.businesslogic.user.User_FindInRoomResponse;
import lv.javaguru.java2.businesslogic.user.User_FindInRoomService;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class Room_JoinOrCreateService {
    
    @Autowired private Room_FindService roomFindService;
    @Autowired private Room_AddService roomAddService;
    @Autowired private Room_NameValidator validator;
    @Autowired private User_FindInRoomService userFindInRoomService;
    @Autowired private Database database;
    @Autowired private User_AddToRoomService userAddToRoomService;
    
    @Transactional
    public Room_JoinOrCreateResponse init( String roomName, User user ) {
        
        List<Error> errors = new ArrayList<>( );
        
        Room room = null;
        // Check if room exists
        Room_FindResponse roomFindResponse = roomFindService.findRoomByName( roomName );
        if ( roomFindResponse.isSuccess( ) ) {
            // use found room
            room = roomFindResponse.getRoom( );
        } else {
            // add new room
            Room_AddResponse roomAddResponse = roomAddService.addRoom( roomName, user.getNickname( ) );
            if ( roomAddResponse.isSuccess( ) ) {
                room = roomAddResponse.getRoom( );
            } else {
                errors.add( new Error( "Failed to create new room" ) );
                return new Room_JoinOrCreateResponse( null, errors, false );
            }
        }
        
        // Check if user is already in that room
        User_FindInRoomResponse userFindInRoomResponse = userFindInRoomService.find( user, room );
        if ( roomFindResponse.isSuccess( ) ) {
            userAddToRoomService.add( user, room );
        }
        return new Room_JoinOrCreateResponse( room, errors, true );
    }
}

package lv.javaguru.java2.businesslogic.userToRoom;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.room.*;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class Room_JoinOrCreateService {
    
    @Autowired private Room_NameValidator validator;
    @Autowired private Database database;
    
    @Transactional
    public Room_JoinOrCreateResponse joinOrCreateRoom( String roomName, User user ) {
        List<Error> errors = validator.validate( roomName );
        if ( errors.isEmpty( ) ) {
            Optional<Room> optionalRoom = database.chatRoom_get( roomName ); // Check if room exists
            // Use found room or add new
            Room room = optionalRoom.orElseGet( ( ) -> database.chatRoom_add( roomName, user.getNickname( ) ) );
            
            // Check if user is already in that room
            boolean search = database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) );
            if ( search ) {
                return new Room_JoinOrCreateResponse( room, null, true ); // User already in that room
            } else {
                database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
                return new Room_JoinOrCreateResponse( room, null, true ); // Add user to room
            }
        } else { // Room name validation errors
            return new Room_JoinOrCreateResponse( null, errors, false );
        }
    }
}

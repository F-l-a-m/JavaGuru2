package lv.javaguru.java2.businesslogic.user;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddUserToRoomService {
    
    @Autowired private Database database;
    
    @Transactional
    public AddUserToRoomResponse add( User user, Room room ) {
        
        List<Error> errors = new ArrayList<>( );
        
        // Check if user exists
        Optional<User> optionalUser = database.user_get( user.getId( ) );
        if ( !optionalUser.isPresent( ) ) {
            errors.add( new Error( "User with nickname " + user.getNickname( ) + " not found" ) );
        }
        // Check if room exists
        Optional<Room> optionalRoom = database.chatRoom_get( room.getId( ) );
        if ( !optionalRoom.isPresent( ) ) {
            errors.add( new Error( "Room with name " + room.getName( ) + " not found" ) );
        }
        if ( !errors.isEmpty( ) ) {
            return new AddUserToRoomResponse( errors, false );
        } else {
            // Check if user is already in that room
            if ( database.userInRoom_findUserInRoom( user.getId( ), room.getId( ) ) ) {
                errors.add( new Error(
                        "User " + user.getNickname( ) + " is already in room " + room.getName( ) + '.'
                ) );
                return new AddUserToRoomResponse( errors, false );
            } else {
                // Add user to room
                database.userInRoom_addUserToRoom( user.getId( ), room.getId( ) );
                return new AddUserToRoomResponse( null, true );
            }
        }
    }
}

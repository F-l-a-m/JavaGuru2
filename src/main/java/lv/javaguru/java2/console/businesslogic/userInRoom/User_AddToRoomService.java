package lv.javaguru.java2.console.businesslogic.userInRoom;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.database.RoomRepository;
import lv.javaguru.java2.console.database.UserInRoomRepository;
import lv.javaguru.java2.console.database.UserRepository;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class User_AddToRoomService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private UserInRoomRepository userInRoomRepository;
    
    @Transactional
    public User_AddToRoomResponse add( User user, Room room ) {
        
        List<Error> errors = new ArrayList<>( );
        
        // Check if user exists
        Optional<User> optionalUser = userRepository.getByNickname( user.getNickname( ) );
        if ( !optionalUser.isPresent( ) ) {
            errors.add( new Error( "User with nickname " + user.getNickname( ) + " not found" ) );
        }
        // Check if room exists
        Optional<Room> optionalRoom = roomRepository.get( room.getName() );
        if ( !optionalRoom.isPresent( ) ) {
            errors.add( new Error( "Room with name " + room.getName( ) + " not found" ) );
        }
        if ( !errors.isEmpty( ) ) {
            return new User_AddToRoomResponse( errors, false );
        } else {
            // Check if user is already in that room
            if ( userInRoomRepository.findUserInRoom( user, room ) ) {
                errors.add( new Error(
                        "User " + user.getNickname( ) + " is already in room " + room.getName( ) + '.'
                ) );
                return new User_AddToRoomResponse( errors, false );
            } else {
                // Add user to room
                userInRoomRepository.addUserToRoom( user, room );
                return new User_AddToRoomResponse( null, true );
            }
        }
    }
}

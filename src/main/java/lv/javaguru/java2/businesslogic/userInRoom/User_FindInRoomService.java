package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.database.UserRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class User_FindInRoomService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private UserInRoomRepository userInRoomRepository;
    
    @Transactional
    public User_FindInRoomResponse find( User user, Room room ) {
        List<Error> errors = new ArrayList<>( );
        
        // Check if user exists
        Optional<User> optionalUser = userRepository.get( user.getNickname( ) );
        if ( !optionalUser.isPresent( ) ) {
            errors.add( new Error( "User with nickname " + user.getNickname( ) + " not found" ) );
        }
        // Check if room exists
        Optional<Room> optionalRoom = roomRepository.get( room.getName( ) );
        if ( !optionalRoom.isPresent( ) ) {
            errors.add( new Error( "Room with name " + room.getName( ) + " not found" ) );
        }
        if ( !errors.isEmpty( ) ) {
            return new User_FindInRoomResponse( errors, false );
        }
        
        // Check if user is already in that room
        if ( userInRoomRepository.findUserInRoom( user, room ) ) {
            return new User_FindInRoomResponse( null, true );
        } else {
            errors.add( new Error(
                    "User " + user.getNickname( ) + " not found in room " + room.getName( ) + '.'
            ) );
            return new User_FindInRoomResponse( errors, false );
        }
    }
}

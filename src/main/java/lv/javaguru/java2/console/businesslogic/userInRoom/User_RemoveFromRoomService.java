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
public class User_RemoveFromRoomService {
    
    @Autowired private UserRepository userRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private UserInRoomRepository userInRoomRepository;
    
    @Transactional
    public User_RemoveFromRoomResponse leaveRoom( String userNickname, String roomName ) {
        Optional<User> optionalUser = userRepository.getByNickname( userNickname );
        List<Error> errors = new ArrayList<>( );
        if ( !optionalUser.isPresent( ) ) {
            errors.add( new Error( "User with nickname " + userNickname + " not found" ) );
            return new User_RemoveFromRoomResponse( null, errors, false );
        } else {
            Optional<Room> optionalRoom = roomRepository.get( roomName );
            if ( !optionalRoom.isPresent( ) ) {
                errors.add( new Error( "Room with nickname " + roomName + " not found" ) );
                return new User_RemoveFromRoomResponse( null, errors, false );
            } else {
                User user = optionalUser.get( );
                Room room = optionalRoom.get( );
                boolean isUserAlreadyInThatRoom = userInRoomRepository.findUserInRoom( user, room );
                if ( isUserAlreadyInThatRoom ) {
                    List<Room> rooms = userInRoomRepository.getAListOfJoinedRooms( user );
                    if ( rooms.size( ) > 1 ) {
                        userInRoomRepository.removeUserFromRoom( user, room );
                        Room roomToReturn = rooms.get( rooms.size( ) - 2 );
                        return new User_RemoveFromRoomResponse( roomToReturn, null, true );
                    } else {
                        errors.add( new Error( "Can't leave last room" ) );
                        return new User_RemoveFromRoomResponse( null, errors, false );
                    }
                } else {
                    errors.add( new Error( "User " + userNickname + " is not in a room " + roomName ) );
                    return new User_RemoveFromRoomResponse( null, errors, false );
                }
            }
        }
    }
}

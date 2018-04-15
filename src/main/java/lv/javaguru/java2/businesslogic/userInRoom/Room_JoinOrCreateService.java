package lv.javaguru.java2.businesslogic.userInRoom;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.MyTimestamp;
import lv.javaguru.java2.businesslogic.room.*;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.database.UserInRoomRepository;
import lv.javaguru.java2.domain.Room;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserInRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.domain.builders.RoomBuilder.createRoom;
import static lv.javaguru.java2.domain.builders.UserInRoomBuilder.createUserInRoom;

@Component
public class Room_JoinOrCreateService {
    
    @Autowired private Room_NameValidator validator;
    @Autowired private UserInRoomRepository userInRoomRepository;
    @Autowired private RoomRepository roomRepository;
    
    @Transactional
    public Room_JoinOrCreateResponse joinOrCreateRoom( String roomName, User user ) {
        List<Error> errors = validator.validate( roomName );
        if ( errors.isEmpty( ) ) {
            Optional<Room> optionalRoom = roomRepository.get( roomName ); // Check if room exists
            // Use found room or save new
            Room room = null;
            room = optionalRoom.orElseGet( ( ) -> createRoom( )
                    .withName( roomName )
                    .withCreatorNickname( user.getNickname( ) )
                    .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                    .build( ) );
            
            // Check if user is already in that room, if not create new record
            boolean search = userInRoomRepository.findUserInRoom( user, room );
            if ( search ) {
                return new Room_JoinOrCreateResponse( room, null, true ); // User already in that room
            } else {
                userInRoomRepository.addUserToRoom( user, room );
                return new Room_JoinOrCreateResponse( room, null, true ); // Add user to room
            }
        } else { // Room name validation errors
            return new Room_JoinOrCreateResponse( null, errors, false );
        }
    }
}

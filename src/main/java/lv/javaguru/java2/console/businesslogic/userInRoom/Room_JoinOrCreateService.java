package lv.javaguru.java2.console.businesslogic.userInRoom;

import lv.javaguru.java2.console.businesslogic.Error;
import lv.javaguru.java2.console.businesslogic.MyTimestamp;
import lv.javaguru.java2.console.businesslogic.room.*;
import lv.javaguru.java2.console.database.RoomRepository;
import lv.javaguru.java2.console.database.UserInRoomRepository;
import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import lv.javaguru.java2.console.domain.UserInRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.console.domain.builders.RoomBuilder.createRoom;
import static lv.javaguru.java2.console.domain.builders.UserInRoomBuilder.createUserInRoom;

@Component
public class Room_JoinOrCreateService {
    
    @Autowired private Room_NameValidator validator;
    @Autowired private UserInRoomRepository userInRoomRepository;
    @Autowired private RoomRepository roomRepository;
    
    @Transactional
    public Room_JoinOrCreateResponse joinOrCreateRoom( String roomName, User user ) {
        List<Error> errors = validator.validate( roomName );
        if ( !errors.isEmpty( ) ) {
            // Room name validation errors
            return new Room_JoinOrCreateResponse( null, errors, false );
        } else {
            // Check if room exists
            Optional<Room> optionalRoom = roomRepository.get( roomName );
            Room room;
            if ( optionalRoom.isPresent( ) ) {
                room = optionalRoom.get( );
                boolean isUserAlreadyInThatRoom = userInRoomRepository.findUserInRoom( user, room );
                if ( isUserAlreadyInThatRoom ) {
                    return new Room_JoinOrCreateResponse( room, null, true );
                } else {
                    userInRoomRepository.addUserToRoom( user, room );
                    return new Room_JoinOrCreateResponse( room, null, true );
                }
            } else {
                room = createRoom( )
                        .withName( roomName )
                        .withCreatorNickname( user.getNickname( ) )
                        .withCreationTime( MyTimestamp.getSQLTimestamp( ) )
                        .build( );
                roomRepository.save( room );
        
                userInRoomRepository.addUserToRoom( user, room );
                return new Room_JoinOrCreateResponse( room, null, true ); // Add user to room
            }
        }
    }
}

package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.database.RoomRepository;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Room_FindService {
    
    @Autowired private RoomRepository roomRepository;
    @Autowired private Room_NameValidator validator;
    
    @Transactional
    public Room_FindResponse find( String roomName ) {
        List<Error> errors = validator.validate( roomName );
        if ( errors.isEmpty( ) ) {
            Optional<Room> optionalRoom = roomRepository.get( roomName );
            if ( optionalRoom.isPresent( ) ) {
                return new Room_FindResponse( optionalRoom.get( ), null, true );
            } else {
                errors.add( new Error( "Room with room name " + roomName + " not found" ) );
                return new Room_FindResponse( null, errors, false );
            }
        } else {
            return new Room_FindResponse( null, errors, false );
        }
    }
}

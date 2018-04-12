package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import lv.javaguru.java2.businesslogic.user.User_NicknameValidator;
import lv.javaguru.java2.database.RoomDAO;
import lv.javaguru.java2.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Room_AddService {
    
    @Autowired private RoomDAO roomDAO;
    @Autowired private Room_NameValidator roomNameValidator;
    @Autowired private User_NicknameValidator nicknameValidator;
    
    @Transactional
    public Room_AddResponse addRoom( String roomName, String creatorNickname ) {
        
        List<Error> roomNameErrors = roomNameValidator.validate( roomName );
        List<Error> nicknameErrors = nicknameValidator.validate( creatorNickname );
        List<Error> errors = new ArrayList<>( );
        errors.forEach( error -> roomNameErrors.add( error ) );
        errors.forEach( error -> nicknameErrors.add( error ) );
        
        if ( errors.isEmpty( ) ) {
            // Search
            Optional<Room> optionalRoom = roomDAO.get( roomName );
            if ( optionalRoom.isPresent( ) ) {
                return new Room_AddResponse( optionalRoom.get( ), null, true );
            } else {
                // Create new
                Room room = roomDAO.add( roomName, creatorNickname );
                return new Room_AddResponse( room, null, true );
            }
        } else {
            return new Room_AddResponse( null, errors, false );
        }
    }
}

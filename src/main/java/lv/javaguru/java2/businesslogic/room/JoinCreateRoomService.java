package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JoinCreateRoomService {
    
    @Autowired private FindRoomService findRoomService;
    @Autowired private AddRoomService addRoomService;
    @Autowired private RoomNameValidator validator;
    
    public JoinCreateRoomResponse init( String roomName, String nickname ) {
        
        // check if user is already in that room
        
        
        List<Error> errors = validator.validate( roomName );
        if ( errors.isEmpty( ) ) {
            
            FindRoomResponse findRoomResponse = findRoomService.findRoomByName( roomName );
            if ( findRoomResponse.isSuccess( ) ) {
                // use found room
                return new JoinCreateRoomResponse( findRoomResponse.getRoom( ), errors, true );
            } else {
                // add new room
                AddRoomResponse addRoomResponse = addRoomService.addRoom( roomName, nickname );
                if ( addRoomResponse.isSuccess( ) ) {
                    return new JoinCreateRoomResponse( addRoomResponse.getRoom( ), errors, true );
                } else
                    return new JoinCreateRoomResponse( null, errors, false );
            }
        } else
            return new JoinCreateRoomResponse( null, errors, false );
    }
}

package lv.javaguru.java2.businesslogic.room;

import lv.javaguru.java2.businesslogic.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializeRoomService {
    
    @Autowired private FindRoomService findRoomService;
    @Autowired private AddRoomService addRoomService;
    @Autowired private RoomNameValidator validator;
    
    public InitializeRoomResponse init( String roomName, String nickname ) {
        
        List<Error> errors = validator.validate( roomName );
        if ( errors.isEmpty( ) ) {
            
            FindRoomResponse findRoomResponse = findRoomService.findRoomByName( roomName );
            if ( findRoomResponse.isSuccess( ) ) {
                // use found room
                return new InitializeRoomResponse( findRoomResponse.getRoom( ), errors, true );
            } else {
                // add new room
                AddRoomResponse addRoomResponse = addRoomService.addRoom( roomName, nickname );
                if ( addRoomResponse.isSuccess( ) ) {
                    return new InitializeRoomResponse( addRoomResponse.getRoom( ), errors, true );
                } else
                    return new InitializeRoomResponse( null, errors, false );
            }
        } else
            return new InitializeRoomResponse( null, errors, false );
    }
}

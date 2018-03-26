package lv.javaguru.java2.businesslogic.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InitializeRoomService {
    
    @Autowired private FindRoomService findRoomService;
    @Autowired private AddRoomService addRoomService;
    
    public InitializeRoomResponse init (String roomName, String nickname) {
        FindRoomResponse findRoomResponse = findRoomService.findRoomByName( roomName );
        if ( findRoomResponse.isSuccess( ) ) {
            // use found room
            return new InitializeRoomResponse( findRoomResponse.getRoom(), null, true );
        } else {
            // add new room
            AddRoomResponse addRoomResponse = addRoomService.addRoom( roomName, nickname );
            if ( addRoomResponse.isSuccess( ) ) {
                return new InitializeRoomResponse( addRoomResponse.getRoom(), null, true );
            } else
                return new InitializeRoomResponse( null, addRoomResponse.getErrors(), false );
        }
    }
}

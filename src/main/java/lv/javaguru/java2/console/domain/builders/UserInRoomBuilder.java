package lv.javaguru.java2.console.domain.builders;

import lv.javaguru.java2.console.domain.Room;
import lv.javaguru.java2.console.domain.User;
import lv.javaguru.java2.console.domain.UserInRoom;

public class UserInRoomBuilder {
    
    private Long id;
    private User user;
    private Room room;
    
    private UserInRoomBuilder( ) {
    }
    
    public static UserInRoomBuilder createUserInRoom( ) {
        return new UserInRoomBuilder( );
    }
    
    public UserInRoom build( ) {
        UserInRoom userInRoom = new UserInRoom( );
        userInRoom.setUser( user );
        userInRoom.setRoom( room );
        return userInRoom;
    }
    
    public UserInRoomBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    
    public UserInRoomBuilder withUser( User user ) {
        this.user = user;
        return this;
    }
    
    public UserInRoomBuilder withRoom( Room room ) {
        this.room = room;
        return this;
    }
}

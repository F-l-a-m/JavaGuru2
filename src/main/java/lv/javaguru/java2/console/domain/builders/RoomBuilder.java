package lv.javaguru.java2.console.domain.builders;

import lv.javaguru.java2.console.domain.Room;

import java.util.Date;

public class RoomBuilder {
    
    private Long id;
    private String name;
    private String creatorNickname;
    private Date creationTime;
    
    private RoomBuilder( ) {
    }
    
    public static RoomBuilder createRoom( ) {
        return new RoomBuilder( );
    }
    
    public Room build( ) {
        Room room = new Room( );
        room.setId( id );
        room.setName( name );
        room.setCreatorNickname( creatorNickname );
        room.setCreationTime( creationTime );
        return room;
    }
    
    public RoomBuilder withId( Long id ) {
        this.id = id;
        return this;
    }
    
    public RoomBuilder withName( String roomName ) {
        this.name = roomName;
        return this;
    }
    
    public RoomBuilder withCreatorNickname( String nickname ) {
        this.creatorNickname = nickname;
        return this;
    }
    
    public RoomBuilder withCreationTime( Date creationTime ) {
        this.creationTime = creationTime;
        return this;
    }
}

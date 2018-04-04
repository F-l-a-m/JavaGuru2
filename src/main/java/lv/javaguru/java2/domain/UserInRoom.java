package lv.javaguru.java2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_in_room")
public class UserInRoom {
    
    @Id
    @Column(name = "user_id")
    Long user_id;
    
    //@Id
    @Column(name = "room_id")
    Long room_id;
    
    public UserInRoom () {
    
    }
    
    public UserInRoom( Long user_id, Long room_id ) {
        this.user_id = user_id;
        this.room_id = room_id;
    }
    
    public Long getUser_id( ) {
        return user_id;
    }
    
    public void setUser_id( Long user_id ) {
        this.user_id = user_id;
    }
    
    public Long getRoom_id( ) {
        return room_id;
    }
    
    public void setRoom_id( Long room_id ) {
        this.room_id = room_id;
    }
    
    @Override
    public int hashCode( ) {
        return super.hashCode( );
    }
    
    @Override
    public boolean equals( Object o ) {
        return super.equals( o );
    }
}

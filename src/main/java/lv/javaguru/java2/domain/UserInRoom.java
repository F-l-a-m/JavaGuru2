package lv.javaguru.java2.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_in_room")
public class UserInRoom {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    
    public User getUser( ) {
        return user;
    }
    
    public void setUser( User user ) {
        this.user = user;
    }
    
    public Room getRoom( ) {
        return room;
    }
    
    public void setRoom( Room room ) {
        this.room = room;
    }
    
    @Override
    public int hashCode( ) {
        return super.hashCode( );
    }
    
    @Override
    public boolean equals( Object o ) {
        return super.equals( o );
    }
    
    @Override
    public String toString( ) {
        return "UserInRoom{" +
                "user=" + user +
                ", room=" + room +
                '}';
    }
}

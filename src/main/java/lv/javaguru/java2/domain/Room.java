package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "chat_room")
public class Room {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "creatorNickname", nullable = false)
    private String creatorNickname;
    
    @Column(name = "creationTime", nullable = false)
    private Date creationTime;
    
    public Long getId( ) {
        return id;
    }
    
    public void setId( Long id ) {
        this.id = id;
    }
    
    public String getName( ) {
        return name;
    }
    
    public void setName( String name ) {
        this.name = name;
    }
    
    public String getCreatorNickname( ) {
        return creatorNickname;
    }
    
    public void setCreatorNickname( String creatorNickname ) {
        this.creatorNickname = creatorNickname;
    }
    
    public Date getCreationTime( ) {
        return creationTime;
    }
    
    public void setCreationTime( Date creationTime ) {
        this.creationTime = creationTime;
    }
    
    @Override
    public String toString( ) {
        return name;
    }
}

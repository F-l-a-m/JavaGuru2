package lv.javaguru.java2.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    
    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;
    
    @Column(name = "creationTime", nullable = false)
    private Date creationTime;
    
    @Column(name = "isActive")
    private boolean isActive;
    
    public Long getId( ) {
        return id;
    }
    
    public void setId( Long id ) {
        this.id = id;
    }
    
    public String getLogin( ) {
        return login;
    }
    
    public void setLogin( String login ) {
        this.login = login;
    }
    
    public String getPassword( ) {
        return password;
    }
    
    public void setPassword( String password ) {
        this.password = password;
    }
    
    public String getNickname( ) {
        return nickname;
    }
    
    public void setNickname( String nickname ) {
        this.nickname = nickname;
    }
    
    public Date getCreationTime( ) {
        return creationTime;
    }
    
    public void setCreationTime( Date creationTime ) {
        this.creationTime = creationTime;
    }
    
    public boolean isActive( ) {
        return isActive;
    }
    
    public void setActive( boolean active ) {
        isActive = active;
    }
    
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass( ) != o.getClass( ) ) return false;
        User user = (User) o;
        return Objects.equals( id, user.id );
    }
    
    @Override
    public int hashCode( ) {
        
        return Objects.hash( id );
    }
    
    @Override
    public String toString( ) {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", creationTime=" + creationTime +
                ", isActive=" + isActive +
                '}';
    }
}

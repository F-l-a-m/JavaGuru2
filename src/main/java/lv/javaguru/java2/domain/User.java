package lv.javaguru.java2.domain;

import java.util.Date;
import java.util.Objects;

public class User {

    private Long id;
    private String login;
    private String password;
    private String nickname;
    private Date creationTime;
    private boolean isActive;
    private String inputString;
    
    public User() {
    
    }

    public User(String nickname){
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public Date getCreationTime( ) {
        return creationTime;
    }
    
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public boolean isActive( ) {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public String getInputString( ) {
        return inputString;
    }
    
    public void setInputString( String inputString ) {
        this.inputString = inputString;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass( ) != o.getClass( )) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
    
    @Override
    public int hashCode( ) {
        
        return Objects.hash(id);
    }
}

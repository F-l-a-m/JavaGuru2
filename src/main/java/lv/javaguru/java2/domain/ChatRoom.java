package lv.javaguru.java2.domain;

import java.util.Date;

public class ChatRoom {

    private Long id;
    private String name;
    private String creatorNickname;
    private Date creationTime;

    public ChatRoom(){

    }

    public ChatRoom(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getCreatorNickname( ) {
        return creatorNickname;
    }
    
    public void setCreatorNickname(String creatorNickname) {
        this.creatorNickname = creatorNickname;
    }
    
    public Date getCreationTime( ) {
        return creationTime;
    }
    
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    @Override
    public String toString(){
        return name;
    }
}

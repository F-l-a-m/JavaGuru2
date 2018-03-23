package lv.javaguru.java2.domain;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private Long id;
    private Long room_id;
    private Date creationTime;
    private String user_nickname;
    private String message_body;
    // [2018/03/01 12:12:12] Flam: Hello chat!
    
    public Message(){

    }

    public Message( Timestamp creationTime, String user_nickname, String message_body, Long room_id) {
        this.room_id = room_id;
        this.creationTime = creationTime;
        this.user_nickname = user_nickname;
        this.message_body = message_body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getCreationTime( ) {
        return creationTime;
    }
    
    public void setCreationTime( Date creationTime ) {
        this.creationTime = creationTime;
    }
    
    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateTime = '[' + dateFormat.format(creationTime) + ']';
        return dateTime + ' ' + user_nickname + ": " + message_body;
    }
}
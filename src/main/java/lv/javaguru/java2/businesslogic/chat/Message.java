package lv.javaguru.java2.businesslogic.chat;

public class Message {

    private Long id;
    private String timestamp;
    private String user_nickname;
    private String message_body;
    private Long room_id;
    // [2018.03.01] Flam: Hello chat!

    public Message(){

    }

    public Message(String timestamp, String user_nickname, String message_body, Long room_id) {
        this.timestamp = timestamp;
        this.user_nickname = user_nickname;
        this.message_body = message_body;
        this.room_id = room_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
        return timestamp + ' ' + user_nickname + ": " + message_body;
    }
}

package lv.javaguru.java2.businesslogic.chat;

public class Message {

    private Long id;
    private String timestamp;
    private String message_body;
    private Long user_id;
    private Long room_id;
    // [2018.03.01] Flam: Hello chat!

    public Message(){

    }

    public Message(String timestamp, String message_body, Long user_id, Long room_id) {
        this.timestamp = timestamp;
        this.message_body = message_body;
        this.user_id = user_id;
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

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    @Override
    public String toString() {
        return timestamp + ' ' + "nickname-will be here" + ": " + message_body;
    }
}

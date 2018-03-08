package lv.javaguru.java2.businesslogic.chat;

public class ChatLine {

    private String timestamp;
    private String nickname;
    private String message;
    // [2018.03.01] Flam: Hello chat!

    public ChatLine(String timestamp, String nickname, String message) {
        this.timestamp = timestamp;
        this.nickname = nickname;
        this.message = message;
    }

    public String getNickname() { return nickname; }

    public String getTimestamp() { return timestamp;  }

    public String getMessage() { return message; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return timestamp + ' ' + nickname + ": " + message;
    }
}

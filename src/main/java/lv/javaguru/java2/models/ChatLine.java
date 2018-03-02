package lv.javaguru.java2.models;

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

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatLine product = (ChatLine) o;

        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        return description != null ? description.equals(product.description) : product.description == null;
    }

    @Override
    public int hashCode() {

        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;

    }*/

    @Override
    public String toString() {
        return timestamp + ' ' + nickname + ": " + message;
    }
}

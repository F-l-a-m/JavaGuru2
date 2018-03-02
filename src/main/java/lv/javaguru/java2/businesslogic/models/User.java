package lv.javaguru.java2.businesslogic.models;

public class User {

    private String nickname;

    public User(){
        int rand = (int)(Math.random() * 100);
        this.nickname = "username" + Integer.toString(rand);
    }

    public User(String nickname){
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}

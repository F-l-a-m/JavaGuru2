package lv.javaguru.java2.businesslogic.user;

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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;
        return this.nickname.equals(user.nickname);
    }
}

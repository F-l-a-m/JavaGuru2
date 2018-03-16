package lv.javaguru.java2.businesslogic.user;

public class User {

    private Long id;
    private String login;
    private String password;
    private String nickname;

    public User(){
        int rand = (int)(Math.random() * 100);
        this.nickname = "guest" + Integer.toString(rand);
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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;
        return this.nickname.equals(user.nickname);
    }
}

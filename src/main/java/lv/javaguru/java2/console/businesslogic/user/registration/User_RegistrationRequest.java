package lv.javaguru.java2.console.businesslogic.user.registration;

public class User_RegistrationRequest {
    
    private String login;
    private String password;
    private String nickname;
    
    public User_RegistrationRequest( String login, String password, String nickname ) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }
    
    public String getLogin( ) {
        return login;
    }
    
    public void setLogin( String login ) {
        this.login = login;
    }
    
    public String getPassword( ) {
        return password;
    }
    
    public void setPassword( String password ) {
        this.password = password;
    }
    
    public String getNickname( ) {
        return nickname;
    }
    
    public void setNickname( String nickname ) {
        this.nickname = nickname;
    }
}

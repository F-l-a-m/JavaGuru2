package lv.javaguru.java2.businesslogic.user.login;

public class User_LoginRequest {
    
    private String login;
    private String password;
    
    public User_LoginRequest( String login, String password ) {
        this.login = login;
        this.password = password;
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
}

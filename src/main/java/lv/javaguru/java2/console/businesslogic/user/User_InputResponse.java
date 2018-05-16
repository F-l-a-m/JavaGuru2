package lv.javaguru.java2.console.businesslogic.user;

public class User_InputResponse {
    
    private final byte command;
    private String data;
    
    public User_InputResponse( byte command, String data ) {
        this.command = command;
        this.data = data;
    }
    
    public byte getCommand( ) {
        return command;
    }
    
    public String getData( ) {
        return data;
    }
    
    public void setData( String data ) {
        this.data = data;
    }
}

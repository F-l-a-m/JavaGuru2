package lv.javaguru.java2.businesslogic;

public class UserInputResponse {
    
    private final byte command;
    private String data;
    
    public UserInputResponse( byte command, String data ) {
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

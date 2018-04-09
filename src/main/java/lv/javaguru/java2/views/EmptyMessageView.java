package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.MyTimestamp;

public class EmptyMessageView implements View {
    
    private final String nickname;
    
    public EmptyMessageView( String nickname ) {
        this.nickname = nickname;
    }
    
    @Override
    public void execute( ) {
        System.out.println( MyTimestamp.getStringTimestamp( ) + ' ' + nickname + ":" );
    }
}

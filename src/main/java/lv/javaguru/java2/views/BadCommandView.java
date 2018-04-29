package lv.javaguru.java2.views;

import org.springframework.stereotype.Component;

@Component
public class BadCommandView implements View {
    
    @Override
    public void execute( ) {
        System.out.println( "Command syntax error. Please try again." );
    }
}

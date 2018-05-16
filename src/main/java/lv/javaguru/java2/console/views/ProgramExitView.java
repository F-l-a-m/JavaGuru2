package lv.javaguru.java2.console.views;

import org.springframework.stereotype.Component;

@Component
public class ProgramExitView implements View {
    @Override
    public void execute( ) {
        System.out.println( "Good bye!" );
        System.exit( 0 );
    }
}
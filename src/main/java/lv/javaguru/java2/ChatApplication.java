package lv.javaguru.java2;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.views.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ChatApplication {
    
    public static void main( String[] args ) {
    
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext( SpringAppConfig.class );
    
        View mainUserInputView = applicationContext.getBean( MainView.class );
        mainUserInputView.execute( );
    }
}

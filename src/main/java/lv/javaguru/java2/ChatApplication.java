package lv.javaguru.java2;

import lv.javaguru.java2.configs.SpringAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ChatApplication implements Constants {
    
    public static void main( String[] args ) {
        
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext( SpringAppConfig.class );
        
        applicationContext.getBean( Launcher.class ).go( );
    }
}

package lv.javaguru.java2.businesslogic;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

@Component
public final class MyTimestamp {
    
    public static String getStringTimestamp( ) {
        DateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
        Date date = new Date( );
        return '[' + dateFormat.format( date ) + ']';
    }
    
    public static Timestamp getSQLTimestamp( ) {
        java.util.Date now = new java.util.Date( );
        return new Timestamp( now.getTime( ) );
    }
}

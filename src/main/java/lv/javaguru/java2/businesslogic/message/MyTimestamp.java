/*
package lv.javaguru.java2.businesslogic.message;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

@Component
public class MyTimestamp {
    
    public String getStringTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return '[' + dateFormat.format(date) + ']';
    }
    
    public Timestamp getSQLTimestamp() {
        java.util.Date now = new java.util.Date();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(now.getTime());
        return timestamp;
    }
    
    @Override
    public String toString() {
        return getStringTimestamp();
    }
}
*/

package lv.javaguru.java2.businesslogic.chat;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Timestamp {
    
    public String getTimestamp(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return '[' + dateFormat.format(date) + ']';
    }
}

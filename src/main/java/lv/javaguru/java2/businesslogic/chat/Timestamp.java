package lv.javaguru.java2.businesslogic.chat;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Timestamp {

    private String timestamp;

    public String getTimestamp(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.timestamp = '[' + dateFormat.format(date) + ']';
        return this.timestamp;
    }
}

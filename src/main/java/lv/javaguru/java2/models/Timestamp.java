package lv.javaguru.java2.models;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Timestamp {

    private String timestamp;

    public String getTimestamp(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
        this.timestamp = '[' + dateFormat.format(date) + ']';

        return this.timestamp;
    }
}

package lv.javaguru.java2.businesslogic.threadTest;

import java.util.ArrayList;
import java.util.List;

public class ThreadLog {
    
    int threadNum;
    String logLine;
    
    public ThreadLog( int threadNum, String logLine ) {
        this.threadNum = threadNum;
        this.logLine = logLine;
    }
    
    public int getThreadNum( ) {
        return threadNum;
    }
    
    public void setThreadNum( int threadNum ) {
        this.threadNum = threadNum;
    }
    
    public String getLogLine( ) {
        return logLine;
    }
    
    public void setLogLine( String logLine ) {
        this.logLine = logLine;
    }
}

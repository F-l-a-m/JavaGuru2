package lv.javaguru.java2.businesslogic.threadTest;

import java.util.List;

public class Add implements Runnable {
    
    BankAccount account;
    List<ThreadLog> logList;
    
    public Add( BankAccount account, List<ThreadLog> logList ) {
        this.account = account;
        this.logList = logList;
    }
    
    @Override
    public void run( ) {
        for ( int i = 0; i < 10000; i++ ) {
            account.setMoney( account.getMoney( ) + 1 );
            account.calcMax( );
            String line = "Thread add: " + account.getName( ) + " = " + account.getMoney( );
            System.out.println( line );
            logList.add( new ThreadLog( 0, line ) );
        }
    }
}

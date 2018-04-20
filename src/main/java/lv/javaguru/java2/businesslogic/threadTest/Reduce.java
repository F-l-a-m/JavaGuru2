package lv.javaguru.java2.businesslogic.threadTest;

import java.util.List;

public class Reduce implements Runnable {
    
    BankAccount account;
    List<ThreadLog> logList;
    
    public Reduce( BankAccount account, List<ThreadLog> logList ) {
        this.account = account;
        this.logList = logList;
    }
    
    @Override
    public void run( ) {
        for ( int i = 0; i < 10000; i++ ) {
            account.setMoney( account.getMoney( ) - 1 );
            account.calcMax( );
            String line = "Thread reduce: " + account.getName( ) + " = " + account.getMoney( );
            System.out.println( line );
            logList.add( new ThreadLog( 1, line ) );
        }
    }
}

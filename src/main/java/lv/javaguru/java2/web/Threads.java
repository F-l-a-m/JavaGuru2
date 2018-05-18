package lv.javaguru.java2.web;

import lv.javaguru.java2.console.businesslogic.threadTest.Add;
import lv.javaguru.java2.console.businesslogic.threadTest.BankAccount;
import lv.javaguru.java2.console.businesslogic.threadTest.Reduce;
import lv.javaguru.java2.console.businesslogic.threadTest.ThreadLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Threads extends HttpServlet {
    
    @Override
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException, IOException {
        
        BankAccount bankAccountOne = new BankAccount( );
        bankAccountOne.setName( "Account One" );
        BankAccount bankAccountTwo = new BankAccount( );
        bankAccountTwo.setName( "Account Two" );
        BankAccount bankAccountThree = new BankAccount( );
        bankAccountThree.setName( "Account Three" );
        bankAccountOne.setMoney( 1000 );
        bankAccountTwo.setMoney( 0 );
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter( );
        
        out.println( "<h1>" + "Thread timing" + "</h1>" );
        out.println( "<p>" + bankAccountOne.getName( ) + " money = " + bankAccountOne.getMoney( ) + "</p>" );
        out.println( "<p>" + bankAccountTwo.getName( ) + " money = " + bankAccountTwo.getMoney( ) + "</p>" );
        out.println( "<p>Transfer 1000 from 1 to 2 account</p>" );
        List<ThreadLog> log = new ArrayList<>( );
        
        // 1000 times add 1
        Runnable bankAccountOneReduceJob = new Reduce( bankAccountOne, log );
        Thread bankAccountOneThread = new Thread( bankAccountOneReduceJob );
        bankAccountOneThread.start( );
        
        // 1000 times reduce 1
        Runnable bankAccountTwoReduceJob = new Add( bankAccountTwo, log );
        Thread bankAccountTwoThread = new Thread( bankAccountTwoReduceJob );
        bankAccountTwoThread.start( );
        
        out.println( "<p> Wait 10 ms ... </p>" );
        try {
            Thread.sleep( 10 );
        } catch (InterruptedException ex) {
        }
        
        out.println( "<p>" + bankAccountOne.getName( ) + " money = " + bankAccountOne.getMoney( ) + "</p>" );
        out.println( "<p>" + bankAccountTwo.getName( ) + " money = " + bankAccountTwo.getMoney( ) + "</p>" );
        
        out.println( "<h1>" + "Thread shared resources" + "</h1>" );
        bankAccountThree.setMoney( 0 );
        out.println( "<p>" + bankAccountThree.getName( ) + " money = " + bankAccountThree.getMoney( ) + "</p>" );
        out.println( "<p>10000 times add and reduce by 1</p>" );
        
        Runnable bankAccount3AddJob = new Add( bankAccountThree, log );
        Thread bankAccount3AddThread = new Thread( bankAccount3AddJob );
        Runnable bankAccount3ReduceJob = new Reduce( bankAccountThree, log );
        Thread bankAccount3ReduceThread = new Thread( bankAccount3ReduceJob );
        bankAccount3AddThread.start( );
        bankAccount3ReduceThread.start( );
        out.println( "<p> Wait 100 ms ... </p>" );
        try {
            Thread.sleep( 100 );
        } catch (InterruptedException ex) {
        }
        out.println( "<p>" + bankAccountThree.getName( ) + " money = " + bankAccountThree.getMoney( ) + "</p>" );
        out.println( "<p>" + bankAccountThree.getName( ) + " maximum money amount was = " + bankAccountThree.getMax( ) + "</p>" );
        
        out.println( "<h1>" + "Thread working log" + "</h1>" );
        out.println( "<table>" +
                "<tr>" +
                "<th> Add thread log</th>" +
                "<th> Reduce thread log</th>" +
                "</tr>" );
        
        for ( ThreadLog item : log ) {
            out.println( "<tr>" );
            if ( item.getThreadNum( ) == 0 ) {
                out.println( "<td>" + item.getLogLine( ) + "</td>" );
                out.println( "<td></td>" );
            } else {
                out.println( "<td></td>" );
                out.println( "<td>" + item.getLogLine( ) + "</td>" );
            }
            out.println( "</tr>" );
        }
        out.println( "</table>" );
    }
}

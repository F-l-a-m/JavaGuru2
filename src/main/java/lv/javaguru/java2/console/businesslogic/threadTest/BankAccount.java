package lv.javaguru.java2.console.businesslogic.threadTest;

public class BankAccount {
    
    private int money;
    private int maxValueOfMoney;
    private String name;
    
    public int getMoney( ) {
        return money;
    }
    
    public void setMoney( int money ) {
        this.money = money;
    }
    
    public String getName( ) {
        return name;
    }
    
    public void setName( String name ) {
        this.name = name;
    }
    
    void calcMax( ) {
        if ( money > maxValueOfMoney ) {
            this.maxValueOfMoney = money;
        }
    }
    
    public int getMax( ) {
        return this.maxValueOfMoney;
    }
}

package lv.javaguru.java2.businesslogic.user;

public final class CurrentUser {
    
    private static User user;
    
    private CurrentUser() { }
    
    public static User getUser( ) {
        return user;
    }
    
    public static void setUser(User user) {
        CurrentUser.user = user;
    }
}

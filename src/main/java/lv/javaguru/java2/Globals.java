package lv.javaguru.java2;

import lv.javaguru.java2.businesslogic.models.User;

public final class Globals {
    private static User user = new User();

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Globals.user = user;
    }
}

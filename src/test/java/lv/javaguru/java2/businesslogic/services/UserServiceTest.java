package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.InMemoryDatabase;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    /*
    @Test
    public void createNewUser(){
        Database database = new InMemoryDatabase();
        UserService userService = new UserService(database);
        database.

        userService.createNewUser();

        database.getCurrentUser();

        assertEquals();
    }*/

    @Test
    public void changeUserNickname() {
        Database database = new InMemoryDatabase();
        UserService userService = new UserService(database);
        userService.createNewUser();
        String newNickname = "NewNickname";
        userService.changeUserNickname(newNickname);
        assertEquals(userService.getCurrentUser().getNickname(), newNickname);
    }
}

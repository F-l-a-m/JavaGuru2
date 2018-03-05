package lv.javaguru.java2.businesslogic.services;

import lv.javaguru.java2.Constants;
import lv.javaguru.java2.database.InMemoryDatabase;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandleUserInputServiceTest {

    @Test
    public void userInputShouldReturnMessageConstant(){
        InMemoryDatabase database = new InMemoryDatabase();
        HandleUserInputService handleUserInputService = new HandleUserInputService(database);
        UserService userService = new UserService(database);
        userService.createNewUser();
        String aString = "kjd34509384590834jklgdfgfhkjg";
        Enum result = handleUserInputService.HandleUserInput(aString);
        assertEquals(result, Constants.userActions.MESSAGE);
    }
}

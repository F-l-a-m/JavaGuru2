package lv.javaguru.java2.views;

import lv.javaguru.java2.businesslogic.services.UserService;
import lv.javaguru.java2.database.Database;

import java.util.Scanner;

public class ChangeNicknameView implements View{

    private UserService userService;

    public ChangeNicknameView(Database database) {
        this.userService = new UserService(database);
    }

    @Override
    public void execute() {
        System.out.print("Please enter your username: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        //Implement to return true if validated
        userService.changeUserNickname(input);

        System.out.println("User nickname set to \'" + input + '\'');

        // Else error
    }
}

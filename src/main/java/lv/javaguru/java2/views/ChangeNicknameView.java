package lv.javaguru.java2.views;

import lv.javaguru.java2.Globals;
import lv.javaguru.java2.businesslogic.models.User;

import java.util.Scanner;

public class ChangeNicknameView implements View{
    @Override
    public void execute() {
        System.out.print("Please enter your username: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Globals.setUser(new User(input));
        System.out.println("User nickname set to \'" + input + '\'');
    }
}

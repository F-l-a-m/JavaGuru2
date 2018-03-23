package lv.javaguru.java2;

import lv.javaguru.java2.configs.SpringAppConfig;
import lv.javaguru.java2.views.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;

class ChatApplication {

    public static void main( String[] args ) {

        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);

        Map<Integer, View> actionMap = new HashMap<>();
        actionMap.put(1, applicationContext.getBean(NewMessageView.class));
        //actionMap.put(2, applicationContext.getBean(JoinChatRoomView.class));
        //actionMap.put(3, applicationContext.getBean(ListAllRoomsView.class));
        actionMap.put(4, applicationContext.getBean(ProgramExitView.class));

        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            View view = actionMap.get(menuItem);
            view.execute();
        }
    }

    private static void printProgramMenu() {
        System.out.println("Program Menu:");
        System.out.println("1. New message");
        System.out.println("2. Enter room");
        System.out.println("3. Print all rooms");
        System.out.println("4. Exit");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
}
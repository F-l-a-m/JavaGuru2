package lv.javaguru.java2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ChatApplication {

    public static void main(String[] args) {
 /*      read message
            read user input
         parse message
         execute
         show message
         save chat history to file
*/

        List<ChatLine> chatLines = new ArrayList<>();
        printProgramHelpOnce();

        while(true) {
            String userInput = readUserInput();
            if (HandleUserMessage(userInput)) {
                ChatLine chatLine = new ChatLine("default", "time", userInput);
                System.out.println("> " + chatLine);
            } else {
                System.out.println("input error");
            }
        }


    }

    private static void setNickname(){

    }

 /*   private static void addChatLine(List<ChatLine> chatLines) {
        System.out.println("> ");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        String description = sc.nextLine();
        ChatLine chatLine = new ChatLine(title, description);
        chatLines.add(chatLine);
        System.out.println("Add chatLine to list execution end!\n");
    }*/

 /*   private static void removeProductFromList(List<ChatLine> chatLines) {
        System.out.println("\nRemove product from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product title:");
        final String title = sc.nextLine();
        Optional<ChatLine> foundProduct = chatLines.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
        if (foundProduct.isPresent()) {
            System.out.println("ChatLine with title " + title + " was found and will be removed from list!");
            ChatLine chatLine = foundProduct.get();
            chatLines.remove(chatLine);
        } else {
            System.out.println("ChatLine with title " + title + " not found and not be removed from list!");
        }
        System.out.println("Remove product from list execution end!\n");
    }*/

 /*   private static void printShoppingListToConsole(List<ChatLine> chatLines) {
        System.out.println();
        System.out.println("Print shopping list to console execution start!\n");
        for (ChatLine chatLine : chatLines) {
            System.out.println(chatLine.getTitle() + "[" + chatLine.getDescription() + "]");
        }
        System.out.println("\nPrint shopping list to console execution end!\n");
    }*/

    private static void printProgramHelpOnce() {
        System.out.println("Commands: q - quit app; n - set nickname;\n");
    }

    private static String readUserInput() {
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static boolean HandleUserMessage(String userRawInput){
        if(userRawInput.equals("")){
            //handle
            return true;
        }
        else if(userRawInput.equals("q")){
            //quit app
            System.exit(0);
            return true;
        }
        else if(userRawInput.equals("n")){
            //setnick
            System.out.println("Please enter your username");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            return true;
        }
        else { // Usual chat message
            return true;
        }
    }

}

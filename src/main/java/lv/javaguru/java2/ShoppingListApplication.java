package lv.javaguru.java2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ShoppingListApplication {

    public static void main(String[] args) {
        // Use cases:
        // 1. Add product to list
        // 2. Remove product from list
        // 3. Print shopping list to console
        // 4. Exit

        List<Product> products = new ArrayList<>();
        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            if (menuItem == 4) {
                break;
            }
            switch (menuItem) {
                case 1: {
                    addProductToList(products);
                    break;
                }
                case 2: {
                    removeProductFromList(products);
                    break;
                }
                case 3: {
                    printShoppingListToConsole(products);
                    break;
                }
            }
        }

    }

    private static void addProductToList(List<Product> products) {
        System.out.println();
        System.out.println("Add product to list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product title:");
        String title = sc.nextLine();
        System.out.print("Enter product description:");
        String description = sc.nextLine();
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        products.add(product);
        System.out.println("Add product to list execution end!");
        System.out.println();
    }

    private static void removeProductFromList(List<Product> products) {
        System.out.println();
        System.out.println("Remove product from list execution start!");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product title:");
        final String title = sc.nextLine();
        Optional<Product> foundProduct = products.stream()
                .filter(p -> p.getTitle().equals(title))
                .findFirst();
        if (foundProduct.isPresent()) {
            System.out.println("Product with title " + title + " was found and will be removed from list!");
            Product product = foundProduct.get();
            products.remove(product);
        } else {
            System.out.println("Product with title " + title + " not found and not be removed from list!");
        }
        System.out.println("Remove product from list execution end!");
        System.out.println();
    }

    private static void printShoppingListToConsole(List<Product> products) {
        System.out.println();
        System.out.println("Print shopping list to console execution start!");
        for (Product product : products) {
            System.out.println(product.getTitle() + "[" + product.getDescription() + "]");
        }
        System.out.println("Print shopping list to console execution end!");
        System.out.println();
    }

    private static void printProgramMenu() {
        System.out.println("Program Menu:");
        System.out.println("1. Add product to list");
        System.out.println("2. Remove product from list");
        System.out.println("3. Print list to console");
        System.out.println("4. Exit");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

}

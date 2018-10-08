/**
 * TextView.java
 */

/**
 * @author Bryan Glogowski
 * @version 3.0
 */

package com.AmazonLite;

import java.util.Scanner;
import java.util.InputMismatchException;

public class TextView extends Component implements ViewInterface {

    private Scanner scanner = new Scanner(System.in);
    private boolean waitingForInput = true;

    private Controller controller;

    public void start() {
        displayMainMenu();
    }

    private void displayMainMenu() {

        System.out.println("\nMenu Options\n");

        System.out.println("\t1. Create a new item.");
        System.out.println("\t2. Display an inventory item.");
        System.out.println("\t3. Modify or Update an inventory item.");
        System.out.println("\t4. Delete an item from the inventory.");
        System.out.println("\t5. List all items in the inventory.");
        System.out.println("\t6. Exit the program.\n");

        switch (requestInteger("Please type the number of the menu option you want: ")) {
        case 1:
            displayCreateItemMenu();
            break;
        case 2:
            displayInventoryItemMenu();
            break;
        case 3:
            displayUpdateItemMenu();
            break;
        case 4:
            displayDeleteItemMenu();
            break;
        case 5:
            controller.readInventoryItems();
            break;
        case 6:
            log.info("com.AmazonLite.TextView.displayMainMenu() exiting the program...");
            System.exit(0);
            break;
        default:
            displayMainMenu();
            break;
        }

    }

    private void displayCreateItemMenu() {

        String description;
        Integer quantity;
        Integer pages;

        System.out.println("\nCreate Item Menu Options\n");
        System.out.println("\t1. Create a new book item.");
        System.out.println("\t2. Create a new CD item.");
        System.out.println("\t3. Create a new DVD item.");
        System.out.println("\t4. List all items in the inventory.");
        System.out.println("\t5. Return to the Main Menu.");

        switch (requestInteger("Please type the number of the menu option you want: ")) {
        case 1:
            description = requestString("Please type a description of the book you want to add: ");
            quantity = requestInteger("How many of the books do you want to add to the inventory? ");
            pages = requestInteger("How many pages does the book have? ");

            controller.createItem(description, quantity, pages);
            break;
        case 2:
            description = requestString("Please type a description of the book you want to add: ");
            quantity = requestInteger("How many of the books do you want to add to the inventory? ");

            controller.createItem("CD", description, quantity);
            break;
        case 3:
            description = requestString("Please type a description of the book you want to add: ");
            quantity = requestInteger("How many of the books do you want to add to the inventory? ");

            controller.createItem("DVD", description, quantity);
            break;
        case 4:
            controller.readInventoryItems();
            break;
        case 5:
            displayMainMenu();
            break;
        default:
            displayCreateItemMenu();
            break;
        }

    }

    private void displayInventoryItemMenu() {

        System.out.println("\nDisplay Inventory Item Menu Options\n");
        System.out.println("\t1. Search the inventory by GUID.");
        System.out.println("\t2. List all Books in the inventory.");
        System.out.println("\t3. List all CDs in the inventory.");
        System.out.println("\t4. List all DVDs in the inventory.");
        System.out.println("\t5. Return to the Main Menu.");

        switch (requestInteger("Please type the number of the menu option you want: ")) {
        case 1:
            controller.readInventoryItems(requestInteger("Please type the GUID of the item you want to see: "));
            break;
        case 2:
            controller.readInventoryItems("books");
            break;
        case 3:
            controller.readInventoryItems("cds");
            break;
        case 4:
            controller.readInventoryItems("dvds");
            break;
        case 5:
            displayMainMenu();
            break;
        default:
            displayInventoryItemMenu();
            break;
        }

    }

    private void displayUpdateItemMenu() {

        Integer guid;
        String description;
        Integer quantity;

        System.out.println("\nModify or Update Item Menu Options\n");

        guid = requestInteger("GUID of item to update: ");
        description = requestString("Description: ");
        quantity = requestInteger("Quantity: ");

        controller.updateItem(guid, description, quantity);

    }

    private void displayDeleteItemMenu() {

        System.out.println("\nDelete Item Menu Options\n");
        System.out.println("\t1. List all items in the inventory.");

        System.out.println("\t2. Delete item from the inventory by GUID.");
        System.out.println("\t3. Return to the Main Menu.\n");

        switch (requestInteger("Please type the number of the menu option you want: ")) {
        case 1:
            controller.readInventoryItems();
            break;
        case 2:
            controller.deleteItem(requestInteger("Please type the GUID of the item you want to delete: "));
            break;
        case 3:
            displayMainMenu();
            break;
        default:
            displayDeleteItemMenu();
            break;
        }

    }

    private Integer requestInteger(String prompt) {
        log.info("com.AmazonLite.TextView.requestInteger(String)");

        Integer i = null;

        waitingForInput = true;
        while (waitingForInput) {
            try {
                log.info("com.AmazonLite.TextView.requestInteger(String) input:");
                System.out.print(prompt);
                i = scanner.nextInt();
                // throw away the \n not consumed by nextInt()
                scanner.nextLine();
                waitingForInput = false;
                log.info("com.AmazonLite.TextView.requestInteger(String) input: success.");
            } catch (InputMismatchException e) {
                log.severe("com.AmazonLite.TextView.requestInteger(String) input: failed.");
                System.out.println("The input you provided did not match the prompt.\nPlease try again...");
                scanner.next();
            }
        }
        return i;
    }

    private String requestString(String prompt) {
        log.info("com.AmazonLite.TextView.requestString(String)");

        String s = null;

        waitingForInput = true;
        while (waitingForInput) {
            try {
                log.info("com.AmazonLite.TextView.requestString(String) input:");
                System.out.print(prompt);
                s = scanner.nextLine();
                waitingForInput = false;
                log.info("com.AmazonLite.TextView.requestString(String) input: success.");
            } catch (Exception e) {
                log.severe(e.getMessage());
                log.severe("com.AmazonLite.TextView.requestString(String) input: failed.");
                scanner.next();
            }
        }
        return s;
    }

    public Controller getController() {
        log.info("com.AmazonLite.View.getController()");
        return controller;
    }

    /**
     * @param controller
     *            the Controller to set
     */
    public void setController(Controller controller) {
        log.info("com.AmazonLite.View.setController(Controller)");
        this.controller = controller;
    }

    public void update(Object[][] obj) {
        log.info("com.AmazonLite.TexttView.update(Event)");
        System.out.println();
        
        switch (Thread.currentThread().getStackTrace()[2].getMethodName()) {
        case "create":
            System.out.println("Item created:");
            break;
        case "read":
            System.out.println("List of Items:");
            break;
        case "update":
            System.out.println("Items Modified:");
            break;
        case "delete":
            System.out.println("Items Deleted:");
            break;
        default:
            break;
        }

        for (Object[] row : obj) {
            System.out.print("guid=" + row[0]);
            System.out.print(", ");
            System.out.print("description=" + row[1]);
            System.out.print(", ");
            System.out.print("quantity=" + row[2]);
            System.out.println();
        }
        
        System.out.println();
        displayMainMenu();

    };

    public void refresh() {
        log.info("com.AmazonLite.TextView.refresh()");
        displayMainMenu();
    };

    public void reset() {
        log.info("com.AmazonLite.TextView.reset()");
        displayMainMenu();
    };

}

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashRegisterSystem {
    static Scanner scan = new Scanner(System.in);

    static ArrayList<String> item = new ArrayList<String>();
    static ArrayList<Double> prices = new ArrayList<Double>();
    static ArrayList<Integer> itemQuantity = new ArrayList<Integer>();
    
    static ArrayList<String> usernames = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();
    static String currentUser = ""; // Track logged-in user

public static void userSignup() {
    String username = "^[a-zA-Z0-9]{5,15}$";
    String password = "^(?=.*[0-9])(?=.*[A-Z])[0-9A-Za-z]{8,20}$";

    while(true) {
        System.out.println("----------------SIGN UP----------------");
        System.out.print("Create a Username (alphanumeric and 5-15 characters): ");
        String user = scan.nextLine();
        if(!user.matches(username)) {
            System.out.print("Invalid Username. Please try again.");
            continue;
        }
        
        System.out.print("Create a Password (atleast one uppercase, one letter, and be 8-20 characters): ");
        String pass = scan.nextLine();
        if(!pass.matches(password)) {
            System.out.print("Invalid Password. Please try again.");
            continue;
        }

        usernames.add(user);
        passwords.add(pass);
        System.out.println("Signup Successful!");
        break;
    }
   }

public static boolean userLogin() {
    while(true) {
        System.out.println("-----------------LOGIN-----------------");
        System.out.print("Enter Username: ");
        String user = scan.nextLine();
        System.out.print("Enter Password: ");
        String pass = scan.nextLine();

        for(int i = 0; i < usernames.size() && i < passwords.size(); i++) {
            if (usernames.get(i).equals(user) && passwords.get(i).equals(pass)) {
                currentUser = user; // Set current user
                System.out.println("Login Successfull! Welcome " + user + "!");
                System.out.println("----------------------------------------------------");
                return true;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
    }
   }

public static void menu() {
    System.out.println("----------------------------------------------------");
    System.out.println("|                       Menu:                      |");
    System.out.println("|                                                  |");
    System.out.println("|                      Coffee:                     |");
    System.out.println("|                Americano | PHP 80                |");
    System.out.println("|               Cappuccino | PHP 90                |");
    System.out.println("|                  Matcha | PHP 95                 |");
    System.out.println("|           White Choco Mocha | PHP 100            |");
    System.out.println("|              Hazelnut Latte | PHP 100            |");
    System.out.println("|               Seasalt Latte | PHP 100            |");
    System.out.println("|                                                  |");
    System.out.println("|                      Snacks:                     |");
    System.out.println("|                Hamburger | PHP 65                |");
    System.out.println("|                Hashbrown | PHP 65                |");
    System.out.println("|              French Fries | PHP 60               |");
    System.out.println("|         Ham & Cheese Sandwhich | PHP 70          |");
    System.out.println("|              Tuna Sandwhich | PHP 70             |");
    System.out.println("|                  Waffles | PHP 50                |");
    System.out.println("----------------------------------------------------");
   }

public static void displayOptions() {
    System.out.println("Please pick an option below:");
    System.out.println("1. Menu");
    System.out.println("2. Add Item");
    System.out.println("3. Display Item");
    System.out.println("4. Update Quantity"); // <-- Added
    System.out.println("5. Delete Item");
    System.out.println("6. Proceed to Payment");
    System.out.println("7. Exit");
    System.out.print("Enter Option: ");
}

public static void addProducts() {
    while(true) {
        System.out.println("----------------------------------------------------");
        System.out.print("Enter Item Name: ");
        String itemName = scan.nextLine();

        double price = 0;
        while (true) {
            System.out.print("Enter Price: ");
            try {
                price = scan.nextDouble();
                scan.nextLine();
                if (price < 0) {
                    System.out.println("Price cannot be negative.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid price.");
                scan.nextLine();
            }
        }

        int quantity = 0;
        while (true) {
            System.out.print("Enter Quantity: ");
            try {
                quantity = scan.nextInt();
                scan.nextLine();
                if (quantity < 1) {
                    System.out.println("Quantity must be at least 1.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid quantity.");
                scan.nextLine();
            }
        }

        item.add(itemName);
        prices.add(price);
        itemQuantity.add(quantity);

        System.out.println("Item added successfully.");
        System.out.print("Do you want to add another item? (yes/no): ");
        String addItem = scan.nextLine();
        if(addItem.toLowerCase().equals("yes")) {
            continue;
        } else if(addItem.toLowerCase().equals("no")) {
            System.out.println("----------------------------------------------------");
            break;
        }
        System.out.println();
    }
   }

public static void displayItems() {
    if(item.isEmpty()) {
        System.out.println("There are no items added.");
        return;
    }
    for(int i = 0; i < item.size(); i++) {
        System.out.println((i + 1) + ". " + item.get(i) + " | Price: PHP " + prices.get(i) + " | Quantity: " + itemQuantity.get(i));
    }
    System.out.println("----------------------------------------------------");
   }

public static void removeItems() {
    if(item.isEmpty()) {
        System.out.println("There are no items to remove.");
        System.out.println("----------------------------------------------------");
        return;
    }
    displayItems();

    int remItem = 0;
    while (true) {
        System.out.print("Enter the item number to remove: ");
        try {
            remItem = scan.nextInt();
            scan.nextLine();
            if(remItem < 1 || remItem > item.size()) {
                System.out.println("Invalid item number.");
                continue;
            }
            break;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid item number.");
            scan.nextLine();
        }
    }

    item.remove(remItem - 1);
    prices.remove(remItem - 1);
    itemQuantity.remove(remItem - 1);

    System.out.println("Item removed successfully.");
    System.out.println("----------------------------------------------------");
   }

public static void updateQuantity() {
    if(item.isEmpty()) {
        System.out.println("There are no items to update.");
        System.out.println("----------------------------------------------------");
        return;
    }
    displayItems();
    int index = 0;
    while (true) {
        System.out.print("Enter the item number to update quantity: ");
        try {
            index = scan.nextInt();
            scan.nextLine();
            if (index < 1 || index > item.size()) {
                System.out.println("Invalid item number.");
                continue;
            }
            break;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid item number.");
            scan.nextLine();
        }
    }
    int newQuantity = 0;
    while (true) {
        System.out.print("Enter new quantity: ");
        try {
            newQuantity = scan.nextInt();
            scan.nextLine();
            if (newQuantity < 1) {
                System.out.println("Quantity must be at least 1.");
                continue;
            }
            break;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            scan.nextLine();
        }
    }
    itemQuantity.set(index - 1, newQuantity);
    System.out.println("Quantity updated successfully.");
    System.out.println("----------------------------------------------------");
}

public static void logTransaction(double total, double payment, double change) {
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String dateTime = dtf.format(LocalDateTime.now());
       try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true))) {
           writer.write("Date/Time: " + dateTime);
           writer.newLine();
           writer.write("Cashier: " + currentUser);
           writer.newLine();
           writer.write("Items Purchased:");
           writer.newLine();
           for (int i = 0; i < item.size(); i++) {
               writer.write("  - " + item.get(i) + " | Qty: " + itemQuantity.get(i) + " | Price: PHP " + prices.get(i));
               writer.newLine();
           }
           writer.write("Total: PHP " + total);
           writer.newLine();
           writer.write("Payment: PHP " + payment);
           writer.newLine();
           writer.write("Change: PHP " + change);
           writer.newLine();
           writer.write("----------------------------------------------------");
           writer.newLine();
       } catch (IOException e) {
           System.out.println("Failed to log transaction.");
           e.printStackTrace();
       }
   }

public static void cashPayment() {
    if(item.isEmpty()) {
        System.out.println("No items to process for payment.");
        System.out.println("----------------------------------------------------");
        return;
    }

    double total = 0;

    for(int i = 0; i <item.size(); i++) {
        total += prices.get(i) * itemQuantity.get(i);
    }
    System.out.println("----------------------------------------------------");
    System.out.println("Total Price: PHP " + total);

    double payment = 0;
    while (true) {
        System.out.print("Enter Payment Amount: ");
        try {
            payment = scan.nextDouble();
            scan.nextLine();
            if (payment < total) {
                System.out.println("Insufficient payment. Please enter an amount greater than or equal to the total.");
            } else {
                break;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scan.nextLine();
        }
    }

    double change = payment - total;
    System.out.println("Payment successful. Your change is PHP " + change);

    logTransaction(total, payment, change);

    item.clear();
    prices.clear();
    itemQuantity.clear();
    System.out.println("Items cleared for the next transaction.");
    System.out.println("----------------------------------------------------");
   }

public static void main(String []args) {
    System.out.println("=============Welcome to Brew & Bloom!===============");
    System.out.println("1. Signup");
    System.out.println("2. Login (must have an account)");
    System.out.println("3. Exit");

    int option = 0;
    while (true) {
        System.out.print("Enter Option: ");
        try {
            option = scan.nextInt();
            scan.nextLine();
            if (option < 1 || option > 3) {
                System.out.println("Invalid Option. Please try again.");
                continue;
            }
            break;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine();
        }
    }
    System.out.println("====================================================");

    if (option == 1) {
     userSignup();
    }else if (option == 2) {
     userLogin();
    }else if (option == 3) {
        System.out.println("Exiting the program. Thank you!");
        System.exit(0);
    }
    
    boolean loggedIn = userLogin();
    if(loggedIn) {
        boolean process = true;
        while(process) {
            displayOptions();
            int choice = 0;
            while (true) {
                try {
                    choice = scan.nextInt();
                    scan.nextLine();
                    if (choice < 1 || choice > 7) { 
                        System.out.println("Invalid Option. Please try again.");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scan.nextLine();
                }
            }

            switch(choice) {
                case 1:
                menu();
                break;
                case 2:
                addProducts();
                break;
                case 3:
                displayItems();
                break;
                case 4:
                updateQuantity(); 
                break;
                case 5:
                removeItems();
                break;
                case 6:
                cashPayment();
                break;
                case 7:
                System.out.println("-----------------------------------------------------");
                System.out.println("|                                                   |");
                System.out.println("|Thank you for using Brew and Bloom's Cash Register!|");
                System.out.println("|                                                   |");
                System.out.println("-----------------------------------------------------");
                process = false;
                break;
                default:
                System.out.println("Invalid Option. Please try again.");
                System.out.println("----------------------------------------------------");
            }
        }
    }
   }
}

import java.io.IOException;
import java.util.Scanner;

import bank.AccountActionHandler;
import bank.Bank;
import customer.CustomerFileHandler;
import customer.CustomerHandler;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final AccountActionHandler accountActionHandler = new AccountActionHandler();

    public static void main(String[] args) {
        CustomerFileHandler handler = new CustomerFileHandler();

        try {
            handler.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            printMenu();
            handler.finilizeFile();
        }
    }

    private static void printMenu() {
        System.out.println("Please Select an Option:");
        System.out.println("1. Create User");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Fund Transfer");
        System.out.println("5. Exit");

        try {
            int option = in.nextInt();
            processMenuOption(option);
        } catch (Exception e) {
            System.out.println("Invalid Input. Please enter a number.");
            in.nextLine(); // Clear invalid input
        }
    }

    private static void processMenuOption(int option) {
        CustomerHandler customerHandler = new CustomerHandler();
        int customerId;

        switch (option) {
            case 1:
                customerHandler.addCustomer();
                break;
            case 2:
                customerId = authenticate();
                if (customerId != -1) {
                    deposit(customerId);

                } else {
                    System.out.println("Invalid User");
                }
                break;
            case 3:
                customerId = authenticate();
                if (customerId != -1) {
                    withdraw(customerId);
                } else {
                    System.out.println("Invalid User");
                }
                break;
            case 4:
                customerId = authenticate();
                if (customerId != -1) {
                    transfer(customerId);
 
                } else {
                    System.out.println("Invalid User");
                }
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    private static int authenticate() {
        CustomerHandler customerHandler = new CustomerHandler();
        System.out.println("Enter the CustomerId: ");
        int customerId = in.nextInt();
        System.out.println("Enter the Password: ");
        String password = in.next();

        if (customerHandler.authentication(customerId, password)) {
            return customerId;
        } else {
            return -1; // Indicates authentication failure
        }
    }

    private static void deposit(int customerId) {
        System.out.println("Enter the Amount to be deposited:");
        double amount = in.nextDouble();
        if (accountActionHandler.deposit(customerId, amount)) {
        	System.out.println(Bank.customersMap.get(customerId).toString());
            System.out.println("Successfully Deposited");
        } else {
            System.out.println("Deposit Failed");
        }
    }

    private static void withdraw(int customerId) {
        System.out.println("Enter the Amount to be Withdrawn:");
        double amount = in.nextDouble();
        if (accountActionHandler.withDraw(customerId, amount)) {
        	System.out.println(Bank.customersMap.get(customerId).toString());
            System.out.println("Successfully Completed");
        } else {
            System.out.println("Withdrawal Failed");
        }
    }

    private static void transfer(int customerId) {
        System.out.println("Enter the Receiver's CustomerId:");
        int toCustomerId = in.nextInt();
        System.out.println("Enter the Amount to be transferred:");
        double amount = in.nextDouble();
        if (accountActionHandler.transfer(customerId, toCustomerId, amount)) {
        	System.out.println("Sender Account Status:"+Bank.customersMap.get(customerId).toString());
            System.out.println("Recevier Account Status:"+Bank.customersMap.get(toCustomerId).toString());
            System.out.println("Transferred Successfully");
        } else {
            System.out.println("Transfer Failed");
        }
    }
}

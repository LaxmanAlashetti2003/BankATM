import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankServer {
    private Map<String, BankAccount> accounts; // Stores user accounts
    private Scanner scanner;
    
    public BankServer() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);

        // Sample Users (AccountNumber -> BankAccount)
        accounts.put("1001", new BankAccount("1001", "1234", 10000));  // User 1
        accounts.put("1002", new BankAccount("1002", "5678", 15000));  // User 2
    }

    // User Authentication
    private BankAccount authenticateUser() {
        System.out.print("Enter Account Number: ");
        String accNumber = scanner.next();

        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        if (accounts.containsKey(accNumber) && accounts.get(accNumber).verifyPIN(pin)) {
            System.out.println("Login successful!\n");
            return accounts.get(accNumber);
        } else {
            System.out.println("Invalid Account Number or PIN!");
            return null;
        }
    }

    public void start() {
        System.out.println("--- Welcome to Secure Multi-User Bank ATM ---");
        
        while (true) {
            BankAccount user = authenticateUser();
            if (user == null) {
                System.out.println("Try again.\n");
                continue;
            }

            // ATM Menu
            while (true) {
                System.out.println("\n--- ATM MENU ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw Cash");
                System.out.println("3. Deposit Cash");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        user.getBalance();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ₹");
                        double withdrawAmount = scanner.nextDouble();
                        user.getCash(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ₹");
                        double depositAmount = scanner.nextDouble();
                        user.depositCash(depositAmount);
                        break;
                    case 4:
                        System.out.println("Logged out successfully!");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
                
                if (choice == 4) break;
            }
        }
    }

    public static void main(String[] args) {
        BankServer bank = new BankServer();
        bank.start();
    }
}

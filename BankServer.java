import java.util.Scanner;

class BankServer extends BankAccount {
    private final String userPIN = "1234"; // Default PIN for authentication

    public BankServer(double initialDeposit) {
        super(initialDeposit);
    }

    // Function to verify PIN
    private boolean verifyPIN() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Enter your PIN: ");
            String enteredPIN = scanner.next();

            if (enteredPIN.equals(userPIN)) {
                System.out.println("Authentication successful!\n");
                return true;
            } else {
                attempts--;
                System.out.println("Incorrect PIN! Attempts remaining: " + attempts);
            }

            if (attempts == 0) {
                System.out.println("Too many incorrect attempts! Account locked.");
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BankServer userAccount = new BankServer(10000); // Single User with ₹10,000 deposit
        CDM depositMachine = new CDM(userAccount);
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to the Secure Bank ATM ---");

        // Authenticate User Before Proceeding
        if (!userAccount.verifyPIN()) {
            System.exit(0);
        }

        while (true) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    userAccount.getBalance();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    userAccount.getCash(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = scanner.nextDouble();
                    depositMachine.depositCash(depositAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

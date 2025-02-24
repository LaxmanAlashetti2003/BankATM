import java.util.ArrayList;
import java.util.List;

class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;
    private String securityQuestion;
    private String securityAnswer;
    private List<String> transactionHistory; 

    public BankAccount(String accountNumber, String pin, double initialDeposit, String securityQuestion, String securityAnswer) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialDeposit;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer.toLowerCase(); // Case-insensitive match
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial deposit: ₹" + initialDeposit);
    }

    public boolean verifyPIN(String enteredPIN) {
        return this.pin.equals(enteredPIN);
    }

    public boolean verifySecurityAnswer(String answer) {
        return this.securityAnswer.equals(answer.toLowerCase());
    }

    public void resetPIN(String newPIN) {
        this.pin = newPIN;
        System.out.println("PIN reset successful! Please log in with your new PIN.");
    }

    public void getBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void getCash(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            transactionHistory.add("Failed withdrawal attempt of ₹" + amount + " (Insufficient funds)");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful! New Balance: ₹" + balance);
            transactionHistory.add("Withdrew ₹" + amount + " | Remaining Balance: ₹" + balance);
        }
    }

    public void depositCash(double amount) {
        balance += amount;
        System.out.println("Deposit successful! New Balance: ₹" + balance);
        transactionHistory.add("Deposited ₹" + amount + " | New Balance: ₹" + balance);
    }

    public void viewTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }
}

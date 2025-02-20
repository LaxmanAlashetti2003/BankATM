import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class BankAccount implements ATM {
    protected double balance;
    protected final double MIN_BALANCE = 1000;  // Minimum balance required
    protected final double MAX_WITHDRAWAL = 5000; // Max withdrawal per transaction

    public BankAccount(double initialDeposit) {
        this.balance = initialDeposit;
    }

    // Check balance
    @Override
    public void getBalance() {
        System.out.println("Current Balance: ₹" + balance);
        logTransaction("Checked balance: ₹" + balance);
    }

    // Withdraw cash with limits
    @Override
    public void getCash(double amount) {
        if (amount > MAX_WITHDRAWAL) {
            System.out.println("Error: Cannot withdraw more than ₹" + MAX_WITHDRAWAL);
        } else if ((balance - amount) < MIN_BALANCE) {
            System.out.println("Error: Insufficient balance! Maintain minimum ₹" + MIN_BALANCE);
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful! Remaining balance: ₹" + balance);
            logTransaction("Withdrew ₹" + amount + ", Remaining balance: ₹" + balance);
        }
    }

    // Log transactions
    protected void logTransaction(String log) {
        try (FileWriter writer = new FileWriter("transaction_log.txt", true)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(timestamp + " - " + log + "\n");
        } catch (IOException e) {
            System.out.println("Error logging transaction.");
        }
    }
}

class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;

    public BankAccount(String accountNumber, String pin, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialDeposit;
    }

    public boolean verifyPIN(String enteredPIN) {
        return this.pin.equals(enteredPIN);
    }

    public void getBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    public void getCash(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful! New Balance: ₹" + balance);
        }
    }

    public void depositCash(double amount) {
        balance += amount;
        System.out.println("Deposit successful! New Balance: ₹" + balance);
    }
}

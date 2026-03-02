public class BankAccount {

    private double balance;
    private String pin;
    private static final double WITHDRAWAL_LIMIT = 20000; // Max per transaction

    // Constructor
    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    // Verify PIN
    public boolean verifyPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    // Get Balance
    public double getBalance() {
        return balance;
    }

    // Deposit
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    // Withdraw
    public String withdraw(double amount) {

        if (amount <= 0) {
            return "Invalid amount entered.";
        }

        if (amount > WITHDRAWAL_LIMIT) {
            return "Withdrawal limit exceeded! Max limit is ₹" + WITHDRAWAL_LIMIT;
        }

        if (amount > balance) {
            return "Insufficient balance!";
        }

        balance -= amount;
        return "Withdrawal successful!";
    }
}
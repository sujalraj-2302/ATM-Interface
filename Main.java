public class Main {

    public static void main(String[] args) {

        // Initial balance ₹50,000 and PIN 1234
        BankAccount userAccount = new BankAccount(50000, "1234");

        ATM atm = new ATM(userAccount);

        atm.start();
    }
}
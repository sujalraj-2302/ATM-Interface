import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {

    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("====== WELCOME TO ATM ======");

        // PIN Authentication (3 attempts)
        int attempts = 0;
        boolean authenticated = false;

        while (attempts < 3) {
            System.out.print("Enter your 4-digit PIN: ");
            String enteredPin = scanner.nextLine();

            if (account.verifyPin(enteredPin)) {
                authenticated = true;
                break;
            } else {
                attempts++;
                System.out.println("Incorrect PIN! Attempts left: " + (3 - attempts));
            }
        }

        if (!authenticated) {
            System.out.println("Card blocked due to 3 incorrect attempts.");
            return;
        }

        displayMenu();
    }

    private void displayMenu() {
        int choice = 0;

        do {
            try {
                System.out.println("\n===== ATM MENU =====");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.print("Select option: ");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        System.out.println("Thank you for banking with us!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine(); // clear buffer
            }

        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.println("Available Balance: ₹" + account.getBalance());
    }

    private void depositMoney() {
        try {
            System.out.print("Enter amount to deposit: ₹");
            double amount = scanner.nextDouble();

            if (account.deposit(amount)) {
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Invalid deposit amount.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Enter numeric value.");
            scanner.nextLine();
        }
    }

    private void withdrawMoney() {
        try {
            System.out.print("Enter amount to withdraw: ₹");
            double amount = scanner.nextDouble();

            String result = account.withdraw(amount);
            System.out.println(result);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Enter numeric value.");
            scanner.nextLine();
        }
    }
}

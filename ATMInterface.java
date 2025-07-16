import java.util.Scanner;

// Class representing the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }
}

// Class representing the ATM interface
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("🏦 Welcome to the ATM Machine!");

        boolean running = true;

        while (running) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> withdraw();
                case 2 -> deposit();
                case 3 -> checkBalance();
                case 4 -> {
                    System.out.println("🔒 Thank you for using the ATM. Goodbye!");
                    running = false;
                }
                default -> System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void showMenu() {
        System.out.println("\n==== ATM MENU ====");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("⚠️ Invalid input. Please enter a number.");
            scanner.next(); // discard invalid input
        }
        return scanner.nextInt();
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = getValidDouble();
        if (account.withdraw(amount)) {
            System.out.println("✅ Withdrawal successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("❌ Withdrawal failed. Insufficient balance or invalid amount.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = getValidDouble();
        if (account.deposit(amount)) {
            System.out.println("✅ Deposit successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("❌ Deposit failed. Please enter a positive amount.");
        }
    }

    private void checkBalance() {
        System.out.printf("💰 Current balance: $%.2f\n", account.getBalance());
    }

    private double getValidDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("⚠️ Invalid input. Please enter a valid number.");
            scanner.next(); // discard invalid input
        }
        return scanner.nextDouble();
    }
}

// Main class to run the ATM system
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}

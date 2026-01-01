import java.util.Scanner;

class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid deposit amount.");
            return;
        }
        balance += amount;
        System.out.println(" Amount deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println(" Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println(" Please collect your cash.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class ATM {

    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void start() {

        int choice;

        do {
            System.out.println("\n ATM MENU ");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println(" Thank you for using the ATM.");
                    break;
                default:
                    System.out.println(" Invalid option. Try again.");
            }

        } while (choice != 4);
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    private void checkBalance() {
        System.out.println(" Current Balance: Rs. " + account.getBalance());
    }
}


public class ATMInterface {

    public static void main(String[] args) {

        BankAccount userAccount = new BankAccount(5000); 
        ATM atm = new ATM(userAccount);

        atm.start();
    }
}

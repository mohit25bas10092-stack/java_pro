import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Account> accounts = FileStorage.loadAccounts();
        List<Transaction> transactions = FileStorage.loadTransactions();

        AccountManager accountManager = new AccountManager(accounts);
        TransactionProcessor processor = new TransactionProcessor(accountManager, transactions);

        System.out.println("=================================");
        System.out.println("     BANK ACCOUNT MANAGER");
        System.out.println("=================================");

        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        openAccount(scanner, accountManager, transactions);
                        break;

                    case 2:
                        accountManager.viewAccounts();
                        break;

                    case 3:
                        deposit(scanner, processor);
                        break;

                    case 4:
                        withdraw(scanner, processor);
                        break;

                    case 5:
                        transfer(scanner, processor);
                        break;

                    case 6:
                        statement(scanner, accountManager, transactions);
                        break;

                    case 7:
                        closeAccount(scanner, accountManager);
                        break;

                    case 0:
                        FileStorage.saveAccounts(accounts);
                        FileStorage.saveTransactions(transactions);
                        System.out.println("Data saved. Thank you for using Bank Account Manager.");
                        running = false;
                        break;

                    default:
                        System.out.println("Please choose a number from 0 to 7.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\nWhat do you want to do?");
        System.out.println("1. Open a new account");
        System.out.println("2. View all accounts");
        System.out.println("3. Deposit money");
        System.out.println("4. Withdraw money");
        System.out.println("5. Transfer money");
        System.out.println("6. View mini statement");
        System.out.println("7. Close an account");
        System.out.println("0. Save and exit");
    }

    private static void openAccount(Scanner scanner, AccountManager manager,
                                    List<Transaction> transactions) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter account type (savings/current): ");
        String type = scanner.nextLine();

        double amount = readAmount(scanner, "Enter initial deposit: ");

        String accountNumber = manager.openAccount(name, type, amount);

        if (accountNumber != null) {
            transactions.add(new Transaction(accountNumber, "OPEN", amount, "Account opened"));
        }
    }

    private static void deposit(Scanner scanner, TransactionProcessor processor) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        double amount = readAmount(scanner, "Enter deposit amount: ");
        processor.deposit(accountNumber, amount);
    }

    private static void withdraw(Scanner scanner, TransactionProcessor processor) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        double amount = readAmount(scanner, "Enter withdrawal amount: ");
        processor.withdraw(accountNumber, amount);
    }

    private static void transfer(Scanner scanner, TransactionProcessor processor) {
        System.out.print("Enter sender account number: ");
        String from = scanner.nextLine();

        System.out.print("Enter receiver account number: ");
        String to = scanner.nextLine();

        double amount = readAmount(scanner, "Enter transfer amount: ");
        processor.transfer(from, to, amount);
    }

    private static void statement(Scanner scanner, AccountManager manager,
                                  List<Transaction> transactions) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        Account account = manager.findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        StatementGenerator.showStatement(account, transactions);
    }

    private static void closeAccount(Scanner scanner, AccountManager manager) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        manager.closeAccount(accountNumber);
    }

    private static double readAmount(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);

            try {
                double amount = Double.parseDouble(scanner.nextLine());

                if (amount > 0) {
                    return amount;
                }

                System.out.println("Amount must be positive.");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

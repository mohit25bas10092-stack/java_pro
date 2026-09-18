import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<Account> accounts;

    public AccountManager(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String openAccount(String name, String type, double initialDeposit) {
        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return null;
        }

        if (!type.equalsIgnoreCase("savings") && !type.equalsIgnoreCase("current")) {
            System.out.println("Account type must be savings or current.");
            return null;
        }

        if (initialDeposit <= 0) {
            System.out.println("Initial deposit must be positive.");
            return null;
        }

        if (type.equalsIgnoreCase("savings") && initialDeposit < 500) {
            System.out.println("Savings account requires minimum opening deposit of Rs.500.");
            return null;
        }

        String accountNumber = generateAccountNumber();
        Account account = new Account(accountNumber, name, type.toLowerCase(), initialDeposit);
        accounts.add(account);

        System.out.println("Account opened successfully.");
        System.out.println("Account Number: " + accountNumber);
        return accountNumber;
    }

    private String generateAccountNumber() {
        int highest = 1000;

        for (Account account : accounts) {
            String number = account.getAccountNumber();
            if (number.startsWith("AC")) {
                try {
                    int value = Integer.parseInt(number.substring(2));
                    if (value > highest) {
                        highest = value;
                    }
                } catch (Exception ignored) {
                }
            }
        }

        return "AC" + (highest + 1);
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        System.out.println("\n--- All Accounts ---");
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public boolean closeAccount(String accountNumber) {
        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        if (Math.abs(account.getBalance()) > 0.001) {
            System.out.println("Account cannot be closed. Balance must be zero.");
            return false;
        }

        accounts.remove(account);
        System.out.println("Account closed successfully.");
        return true;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

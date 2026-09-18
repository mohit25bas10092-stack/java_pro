import java.util.List;

public class TransactionProcessor {
    private AccountManager accountManager;
    private List<Transaction> transactions;

    public TransactionProcessor(AccountManager accountManager, List<Transaction> transactions) {
        this.accountManager = accountManager;
        this.transactions = transactions;
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = accountManager.findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return false;
        }

        account.setBalance(account.getBalance() + amount);
        transactions.add(new Transaction(accountNumber, "DEPOSIT", amount, "Money deposited"));

        System.out.println("Deposit successful.");
        System.out.println("New balance: Rs." + String.format("%.2f", account.getBalance()));
        return true;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accountManager.findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return false;
        }

        if (account.isSavings() && account.getBalance() - amount < 500) {
            System.out.println("Withdrawal denied. Savings account must maintain minimum balance of Rs.500.");
            return false;
        }

        if (account.getBalance() - amount < 0) {
            System.out.println("Insufficient balance.");
            return false;
        }

        account.setBalance(account.getBalance() - amount);
        transactions.add(new Transaction(accountNumber, "WITHDRAW", amount, "Money withdrawn"));

        System.out.println("Withdrawal successful.");
        System.out.println("New balance: Rs." + String.format("%.2f", account.getBalance()));
        return true;
    }

    public boolean transfer(String fromNumber, String toNumber, double amount) {
        Account from = accountManager.findAccount(fromNumber);
        Account to = accountManager.findAccount(toNumber);

        if (from == null || to == null) {
            System.out.println("One or both accounts were not found.");
            return false;
        }

        if (from.getAccountNumber().equalsIgnoreCase(to.getAccountNumber())) {
            System.out.println("Cannot transfer to the same account.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return false;
        }

        if (from.isSavings() && from.getBalance() - amount < 500) {
            System.out.println("Transfer denied. Savings account must maintain minimum balance of Rs.500.");
            return false;
        }

        if (from.getBalance() - amount < 0) {
            System.out.println("Insufficient balance.");
            return false;
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        transactions.add(new Transaction(fromNumber, "TRANSFER OUT", amount, "To " + toNumber));
        transactions.add(new Transaction(toNumber, "TRANSFER IN", amount, "From " + fromNumber));

        System.out.println("Transfer successful.");
        return true;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

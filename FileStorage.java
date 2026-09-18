import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {

    private static final String DATA_FOLDER = "data";
    private static final String ACCOUNTS_FILE = DATA_FOLDER + "/accounts.txt";
    private static final String TRANSACTIONS_FILE = DATA_FOLDER + "/transactions.txt";

    public static void saveAccounts(List<Account> accounts) {
        createDataFolder();

        try {
            FileWriter writer = new FileWriter(ACCOUNTS_FILE);

            for (Account account : accounts) {
                writer.write(account.toFileString() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving accounts.");
        }
    }

    public static List<Account> loadAccounts() {
        createDataFolder();

        List<Account> accounts = new ArrayList<>();
        File file = new File(ACCOUNTS_FILE);

        if (!file.exists()) {
            return accounts;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Account account = Account.fromFileString(line);
                    if (account != null) {
                        accounts.add(account);
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading accounts.");
        }

        return accounts;
    }

    public static void saveTransactions(List<Transaction> transactions) {
        createDataFolder();

        try {
            FileWriter writer = new FileWriter(TRANSACTIONS_FILE);

            for (Transaction transaction : transactions) {
                writer.write(transaction.toFileString() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving transactions.");
        }
    }

    public static List<Transaction> loadTransactions() {
        createDataFolder();

        List<Transaction> transactions = new ArrayList<>();
        File file = new File(TRANSACTIONS_FILE);

        if (!file.exists()) {
            return transactions;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Transaction transaction = Transaction.fromFileString(line);
                    if (transaction != null) {
                        transactions.add(transaction);
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading transactions.");
        }

        return transactions;
    }

    private static void createDataFolder() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}

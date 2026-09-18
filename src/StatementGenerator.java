import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StatementGenerator {

    public static void showStatement(Account account, List<Transaction> transactions) {
        System.out.println("\n--- Mini Statement ---");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Name: " + account.getName());
        System.out.println("Type: " + account.getType());
        System.out.println("Current Balance: Rs." + String.format("%.2f", account.getBalance()));

        System.out.println("\nTransaction History:");

        boolean found = false;
        StringBuilder statement = new StringBuilder();

        statement.append("MINI STATEMENT\n");
        statement.append("Account Number: ").append(account.getAccountNumber()).append("\n");
        statement.append("Name: ").append(account.getName()).append("\n");
        statement.append("Type: ").append(account.getType()).append("\n");
        statement.append("Current Balance: Rs.")
                .append(String.format("%.2f", account.getBalance())).append("\n\n");
        statement.append("Transaction History:\n");

        for (Transaction transaction : transactions) {
            if (transaction.getAccountNumber().equalsIgnoreCase(account.getAccountNumber())) {
                System.out.println(transaction);
                statement.append(transaction).append("\n");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No transactions found.");
            statement.append("No transactions found.\n");
        }

        if (account.isSavings()) {
            double interest = InterestCalculator.calculateAnnualInterest(account);
            System.out.println("\nEstimated annual interest at 4%: Rs." +
                    String.format("%.2f", interest));
            statement.append("\nEstimated annual interest at 4%: Rs.")
                    .append(String.format("%.2f", interest)).append("\n");
        }

        saveStatement(account.getAccountNumber(), statement.toString());
    }

    private static void saveStatement(String accountNumber, String text) {
        try {
            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer = new FileWriter("data/statement_" + accountNumber + ".txt");
            writer.write(text);
            writer.close();

            System.out.println("Statement saved to data/statement_" + accountNumber + ".txt");
        } catch (IOException e) {
            System.out.println("Could not save statement.");
        }
    }
}

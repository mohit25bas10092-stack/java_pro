import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String accountNumber;
    private String type;
    private double amount;
    private String details;
    private String dateTime;

    public Transaction(String accountNumber, String type, double amount, String details) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.details = details;
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Transaction(String accountNumber, String type, double amount, String details, String dateTime) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.details = details;
        this.dateTime = dateTime;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String toFileString() {
        return accountNumber + "|" + type + "|" + amount + "|" + details + "|" + dateTime;
    }

    public static Transaction fromFileString(String line) {
        String[] parts = line.split("\\|", 5);
        if (parts.length != 5) {
            return null;
        }

        try {
            return new Transaction(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return dateTime + " | " + type + " | Rs." +
                String.format("%.2f", amount) + " | " + details;
    }
}

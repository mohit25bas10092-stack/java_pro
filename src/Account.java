public class Account {
    private String accountNumber;
    private String name;
    private String type;
    private double balance;

    public Account(String accountNumber, String name, String type, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isSavings() {
        return type.equalsIgnoreCase("savings");
    }

    @Override
    public String toString() {
        return accountNumber + " | " + name + " | " + type + " | Rs." + String.format("%.2f", balance);
    }

    public String toFileString() {
        return accountNumber + "|" + name + "|" + type + "|" + balance;
    }

    public static Account fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 4) {
            return null;
        }

        try {
            return new Account(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
        } catch (Exception e) {
            return null;
        }
    }
}

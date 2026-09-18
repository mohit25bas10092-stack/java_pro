public class InterestCalculator {
    private static final double RATE = 0.04;

    public static double calculateAnnualInterest(Account account) {
        if (!account.isSavings()) {
            return 0;
        }

        return account.getBalance() * RATE;
    }
}

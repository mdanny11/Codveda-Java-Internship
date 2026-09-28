import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public void open(String accountNumber, String holderName, double openingBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account " + accountNumber + " already exists.");
        }
        if (holderName == null || holderName.isBlank()) {
            throw new IllegalArgumentException("Account holder name cannot be empty.");
        }
        if (openingBalance < 0 || Double.isNaN(openingBalance) || Double.isInfinite(openingBalance)) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        accounts.put(accountNumber, new BankAccount(accountNumber, holderName, openingBalance));
    }

    public BankAccount find(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

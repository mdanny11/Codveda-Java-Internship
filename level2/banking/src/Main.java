import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        System.out.println("Simple Banking");
        System.out.println("Enter 5 to exit.");
        boolean running = true;
        while (running) {
            printMenu();
            switch (readChoice(scanner)) {
                case 1 -> openAccount(scanner, bank);
                case 2 -> deposit(scanner, bank);
                case 3 -> withdraw(scanner, bank);
                case 4 -> checkBalance(scanner, bank);
                case 5 -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Choose a number from 1 to 5.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Open account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check balance");
        System.out.println("5. Exit");
        System.out.print("Choice: ");
        System.out.flush();
    }

    private static void openAccount(Scanner scanner, Bank bank) {
        String accountNumber = readText(scanner, "Account number: ");
        if (accountNumber.isEmpty()) {
            System.out.println("Account number cannot be empty.");
            return;
        }
        if (bank.find(accountNumber) != null) {
            System.out.println("Account " + accountNumber + " already exists.");
            return;
        }
        String holderName = readText(scanner, "Account holder: ");
        if (holderName.isEmpty()) {
            System.out.println("Account holder name cannot be empty.");
            return;
        }
        double openingBalance = readDouble(scanner, "Opening balance: ");
        try {
            bank.open(accountNumber, holderName, openingBalance);
            System.out.println("Account opened.");
            System.out.println(bank.find(accountNumber).describe());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void deposit(Scanner scanner, Bank bank) {
        BankAccount account = requireAccount(scanner, bank);
        if (account == null) {
            return;
        }
        double amount = readDouble(scanner, "Amount: ");
        try {
            account.deposit(amount);
            System.out.println("Deposited " + amount + ". Balance: " + account.getBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void withdraw(Scanner scanner, Bank bank) {
        BankAccount account = requireAccount(scanner, bank);
        if (account == null) {
            return;
        }
        double amount = readDouble(scanner, "Amount: ");
        try {
            account.withdraw(amount);
            System.out.println("Withdrew " + amount + ". Balance: " + account.getBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void checkBalance(Scanner scanner, Bank bank) {
        BankAccount account = requireAccount(scanner, bank);
        if (account == null) {
            return;
        }
        System.out.println(account.describe());
    }

    private static BankAccount requireAccount(Scanner scanner, Bank bank) {
        String accountNumber = readText(scanner, "Account number: ");
        if (accountNumber.isEmpty()) {
            System.out.println("Account number cannot be empty.");
            return null;
        }
        BankAccount account = bank.find(accountNumber);
        if (account == null) {
            System.out.println("No account with number " + accountNumber + ".");
            return null;
        }
        return account;
    }

    private static int readChoice(Scanner scanner) {
        while (true) {
            String line = readRequiredLine(scanner);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a whole number.");
                System.out.print("Choice: ");
                System.out.flush();
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.flush();
            String line = readRequiredLine(scanner);
            try {
                double value = Double.parseDouble(line);
                if (Double.isNaN(value) || Double.isInfinite(value)) {
                    System.out.println("Enter a valid number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        System.out.flush();
        return readRequiredLine(scanner);
    }

    private static String readRequiredLine(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            System.out.println();
            System.out.println("Input ended.");
            System.exit(0);
        }
        return scanner.nextLine().trim();
    }
}

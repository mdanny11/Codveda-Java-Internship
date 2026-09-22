import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        System.out.println("Basic Calculator");
        System.out.println("Enter 5 to exit.");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readChoice(scanner);
            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    double first = readNumber(scanner, "First number: ");
                    double second = readNumber(scanner, "Second number: ");
                    runOperation(calculator, choice, first, second);
                    break;
                case 5:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Choose a number from 1 to 5.");
                    break;
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("5. Exit");
        System.out.print("Operation: ");
    }

    private static int readChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a whole number from 1 to 5.");
            scanner.next();
            System.out.print("Operation: ");
        }
        return scanner.nextInt();
    }

    private static double readNumber(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Enter a valid number.");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextDouble();
    }

    private static void runOperation(Calculator calculator, int choice, double first, double second) {
        try {
            double result = switch (choice) {
                case 1 -> calculator.add(first, second);
                case 2 -> calculator.subtract(first, second);
                case 3 -> calculator.multiply(first, second);
                case 4 -> calculator.divide(first, second);
                default -> throw new IllegalStateException("Unexpected operation: " + choice);
            };
            System.out.println("Result: " + result);
        } catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

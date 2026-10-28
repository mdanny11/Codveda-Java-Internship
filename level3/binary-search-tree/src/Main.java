import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("Binary Search Tree");
        System.out.println("Enter 7 to exit.");
        boolean running = true;
        while (running) {
            printMenu();
            switch (readChoice(scanner)) {
                case 1 -> insertValue(scanner, tree);
                case 2 -> deleteValue(scanner, tree);
                case 3 -> searchValue(scanner, tree);
                case 4 -> printTraversal("In-order", tree.inOrder());
                case 5 -> printTraversal("Pre-order", tree.preOrder());
                case 6 -> printTraversal("Post-order", tree.postOrder());
                case 7 -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Choose a number from 1 to 7.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Insert");
        System.out.println("2. Delete");
        System.out.println("3. Search");
        System.out.println("4. In-order traversal");
        System.out.println("5. Pre-order traversal");
        System.out.println("6. Post-order traversal");
        System.out.println("7. Exit");
        System.out.print("Choice: ");
        System.out.flush();
    }

    private static void insertValue(Scanner scanner, BinarySearchTree tree) {
        int value = readInt(scanner, "Value: ");
        if (tree.insert(value)) {
            System.out.println("Inserted " + value + ".");
        } else {
            System.out.println(value + " is already in the tree.");
        }
    }

    private static void deleteValue(Scanner scanner, BinarySearchTree tree) {
        int value = readInt(scanner, "Value: ");
        if (tree.delete(value)) {
            System.out.println("Deleted " + value + ".");
        } else {
            System.out.println(value + " is not in the tree.");
        }
    }

    private static void searchValue(Scanner scanner, BinarySearchTree tree) {
        int value = readInt(scanner, "Value: ");
        if (tree.search(value)) {
            System.out.println(value + " is in the tree.");
        } else {
            System.out.println(value + " is not in the tree.");
        }
    }

    private static void printTraversal(String label, List<Integer> values) {
        if (values.isEmpty()) {
            System.out.println(label + ": tree is empty.");
            return;
        }
        StringBuilder line = new StringBuilder(label).append(":");
        for (int value : values) {
            line.append(" ").append(value);
        }
        System.out.println(line);
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

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.flush();
            String line = readRequiredLine(scanner);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Enter a whole number.");
            }
        }
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

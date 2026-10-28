import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (LibraryStore store = new LibraryStore();
                Scanner scanner = new Scanner(System.in)) {
            run(scanner, store);
        } catch (SQLException ex) {
            System.out.println("Could not open the library database.");
            System.out.println(ex.getMessage());
        }
    }

    private static void run(Scanner scanner, LibraryStore store) {
        System.out.println("Library Management");
        System.out.println("Enter 11 to exit.");
        boolean running = true;
        while (running) {
            printMenu();
            switch (readChoice(scanner)) {
                case 1 -> addBook(scanner, store);
                case 2 -> listBooks(store);
                case 3 -> updateBook(scanner, store);
                case 4 -> deleteBook(scanner, store);
                case 5 -> addUser(scanner, store);
                case 6 -> listUsers(store);
                case 7 -> updateUser(scanner, store);
                case 8 -> deleteUser(scanner, store);
                case 9 -> borrowBook(scanner, store);
                case 10 -> returnBook(scanner, store);
                case 11 -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Choose a number from 1 to 11.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add book");
        System.out.println("2. List books");
        System.out.println("3. Update book");
        System.out.println("4. Delete book");
        System.out.println("5. Add user");
        System.out.println("6. List users");
        System.out.println("7. Update user");
        System.out.println("8. Delete user");
        System.out.println("9. Borrow book");
        System.out.println("10. Return book");
        System.out.println("11. Exit");
        System.out.print("Choice: ");
        System.out.flush();
    }

    private static void addBook(Scanner scanner, LibraryStore store) {
        String title = readText(scanner, "Title: ");
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        String author = readText(scanner, "Author: ");
        if (author.isEmpty()) {
            System.out.println("Author cannot be empty.");
            return;
        }
        try {
            int id = store.addBook(title, author);
            System.out.println("Book added. Id: " + id);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void listBooks(LibraryStore store) {
        try {
            List<String> books = store.listBooks();
            if (books.isEmpty()) {
                System.out.println("No books yet.");
                return;
            }
            System.out.println("Books:");
            for (String book : books) {
                System.out.println(book);
            }
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void updateBook(Scanner scanner, LibraryStore store) {
        int id = readInt(scanner, "Book id: ");
        try {
            if (store.findBook(id) == null) {
                System.out.println("No book with id " + id + ".");
                return;
            }
            String title = readText(scanner, "Title: ");
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }
            String author = readText(scanner, "Author: ");
            if (author.isEmpty()) {
                System.out.println("Author cannot be empty.");
                return;
            }
            store.updateBook(id, title, author);
            System.out.println("Book updated.");
            System.out.println(store.findBook(id));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void deleteBook(Scanner scanner, LibraryStore store) {
        int id = readInt(scanner, "Book id: ");
        try {
            store.deleteBook(id);
            System.out.println("Book deleted.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void addUser(Scanner scanner, LibraryStore store) {
        String name = readText(scanner, "Member name: ");
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        try {
            int id = store.addUser(name);
            System.out.println("User added. Id: " + id);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void listUsers(LibraryStore store) {
        try {
            List<String> users = store.listUsers();
            if (users.isEmpty()) {
                System.out.println("No users yet.");
                return;
            }
            System.out.println("Users:");
            for (String user : users) {
                System.out.println(user);
            }
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void updateUser(Scanner scanner, LibraryStore store) {
        int id = readInt(scanner, "User id: ");
        try {
            if (store.findUser(id) == null) {
                System.out.println("No user with id " + id + ".");
                return;
            }
            String name = readText(scanner, "Member name: ");
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                return;
            }
            store.updateUser(id, name);
            System.out.println("User updated.");
            System.out.println(store.findUser(id));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void deleteUser(Scanner scanner, LibraryStore store) {
        int id = readInt(scanner, "User id: ");
        try {
            store.deleteUser(id);
            System.out.println("User deleted.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void borrowBook(Scanner scanner, LibraryStore store) {
        int bookId = readInt(scanner, "Book id: ");
        try {
            if (store.findBook(bookId) == null) {
                System.out.println("No book with id " + bookId + ".");
                return;
            }
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
            return;
        }
        int userId = readInt(scanner, "User id: ");
        try {
            store.borrowBook(bookId, userId);
            System.out.println("Book borrowed.");
            System.out.println(store.findBook(bookId));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void returnBook(Scanner scanner, LibraryStore store) {
        int bookId = readInt(scanner, "Book id: ");
        try {
            if (store.findBook(bookId) == null) {
                System.out.println("No book with id " + bookId + ".");
                return;
            }
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
            return;
        }
        int userId = readInt(scanner, "User id: ");
        try {
            store.returnBook(bookId, userId);
            System.out.println("Book returned.");
            System.out.println(store.findBook(bookId));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
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

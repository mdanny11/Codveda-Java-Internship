import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibraryStore implements AutoCloseable {
    private final Connection connection;

    public LibraryStore() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:./data/library", "sa", "");
        createTables();
    }

    public int addBook(String title, String author) throws SQLException {
        requireText(title, "Title cannot be empty.");
        requireText(author, "Author cannot be empty.");
        String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.executeUpdate();
            return generatedId(statement);
        }
    }

    public List<String> listBooks() throws SQLException {
        String sql = "SELECT id, title, author, available FROM books ORDER BY id";
        List<String> lines = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet rows = statement.executeQuery(sql)) {
            while (rows.next()) {
                lines.add(bookLine(rows));
            }
        }
        return lines;
    }

    public String findBook(int id) throws SQLException {
        String sql = "SELECT id, title, author, available FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rows = statement.executeQuery()) {
                if (!rows.next()) {
                    return null;
                }
                return bookLine(rows);
            }
        }
    }

    public void updateBook(int id, String title, String author) throws SQLException {
        requireText(title, "Title cannot be empty.");
        requireText(author, "Author cannot be empty.");
        if (findBook(id) == null) {
            throw new IllegalArgumentException("No book with id " + id + ".");
        }
        String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setInt(3, id);
            statement.executeUpdate();
        }
    }

    public void deleteBook(int id) throws SQLException {
        inTransaction(() -> {
            if (findBook(id) == null) {
                throw new IllegalArgumentException("No book with id " + id + ".");
            }
            if (count("SELECT COUNT(*) FROM transactions WHERE book_id = ?", id) > 0) {
                throw new IllegalArgumentException("Cannot delete a book that has borrow history.");
            }
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        });
    }

    public int addUser(String name) throws SQLException {
        requireText(name, "Name cannot be empty.");
        String sql = "INSERT INTO users (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.executeUpdate();
            return generatedId(statement);
        }
    }

    public List<String> listUsers() throws SQLException {
        List<String> lines = new ArrayList<>();
        try (Statement statement = connection.createStatement();
                ResultSet rows = statement.executeQuery("SELECT id, name FROM users ORDER BY id")) {
            while (rows.next()) {
                lines.add(rows.getInt("id") + " | " + rows.getString("name"));
            }
        }
        return lines;
    }

    public String findUser(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM users WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet rows = statement.executeQuery()) {
                if (!rows.next()) {
                    return null;
                }
                return rows.getInt("id") + " | " + rows.getString("name");
            }
        }
    }

    public void updateUser(int id, String name) throws SQLException {
        requireText(name, "Name cannot be empty.");
        if (findUser(id) == null) {
            throw new IllegalArgumentException("No user with id " + id + ".");
        }
        try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ? WHERE id = ?")) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        inTransaction(() -> {
            if (findUser(id) == null) {
                throw new IllegalArgumentException("No user with id " + id + ".");
            }
            if (count("SELECT COUNT(*) FROM transactions WHERE user_id = ?", id) > 0) {
                throw new IllegalArgumentException("Cannot delete a user who has borrow history.");
            }
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        });
    }

    public void borrowBook(int bookId, int userId) throws SQLException {
        inTransaction(() -> {
            if (findUser(userId) == null) {
                throw new IllegalArgumentException("No user with id " + userId + ".");
            }
            Boolean available = lockAvailability(bookId);
            if (available == null) {
                throw new IllegalArgumentException("No book with id " + bookId + ".");
            }
            if (!available) {
                throw new IllegalArgumentException("That book is already borrowed.");
            }
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE books SET available = FALSE WHERE id = ?")) {
                statement.setInt(1, bookId);
                statement.executeUpdate();
            }
            insertTransaction(bookId, userId, "BORROW");
        });
    }

    public void returnBook(int bookId, int userId) throws SQLException {
        inTransaction(() -> {
            if (findUser(userId) == null) {
                throw new IllegalArgumentException("No user with id " + userId + ".");
            }
            Boolean available = lockAvailability(bookId);
            if (available == null) {
                throw new IllegalArgumentException("No book with id " + bookId + ".");
            }
            if (available) {
                throw new IllegalArgumentException("That book is not currently borrowed.");
            }
            if (currentBorrower(bookId) != userId) {
                throw new IllegalArgumentException("That book is borrowed by another user.");
            }
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE books SET available = TRUE WHERE id = ?")) {
                statement.setInt(1, bookId);
                statement.executeUpdate();
            }
            insertTransaction(bookId, userId, "RETURN");
        });
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    private void createTables() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS books (
                        id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        title VARCHAR(200) NOT NULL,
                        author VARCHAR(200) NOT NULL,
                        available BOOLEAN NOT NULL DEFAULT TRUE
                    )
                    """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS users (
                        id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        name VARCHAR(200) NOT NULL
                    )
                    """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS transactions (
                        id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        book_id INT NOT NULL,
                        user_id INT NOT NULL,
                        action VARCHAR(20) NOT NULL,
                        action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (book_id) REFERENCES books (id),
                        FOREIGN KEY (user_id) REFERENCES users (id)
                    )
                    """);
        }
    }

    private void inTransaction(SqlAction action) throws SQLException {
        boolean previous = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try {
            action.run();
            connection.commit();
        } catch (SQLException | RuntimeException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(previous);
        }
    }

    private Boolean lockAvailability(int bookId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT available FROM books WHERE id = ? FOR UPDATE")) {
            statement.setInt(1, bookId);
            try (ResultSet rows = statement.executeQuery()) {
                if (!rows.next()) {
                    return null;
                }
                return rows.getBoolean("available");
            }
        }
    }

    private int currentBorrower(int bookId) throws SQLException {
        String sql = """
                SELECT user_id, action FROM transactions
                WHERE book_id = ?
                ORDER BY id DESC
                LIMIT 1
                """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            try (ResultSet rows = statement.executeQuery()) {
                if (!rows.next() || !"BORROW".equals(rows.getString("action"))) {
                    throw new IllegalArgumentException("That book is not currently borrowed.");
                }
                return rows.getInt("user_id");
            }
        }
    }

    private void insertTransaction(int bookId, int userId, String action) throws SQLException {
        String sql = "INSERT INTO transactions (book_id, user_id, action) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.setString(3, action);
            statement.executeUpdate();
        }
    }

    private int count(String sql, int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet rows = statement.executeQuery()) {
                rows.next();
                return rows.getInt(1);
            }
        }
    }

    private int generatedId(PreparedStatement statement) throws SQLException {
        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (!keys.next()) {
                throw new SQLException("The database did not return a new id.");
            }
            return keys.getInt(1);
        }
    }

    private String bookLine(ResultSet rows) throws SQLException {
        String status = rows.getBoolean("available") ? "available" : "borrowed";
        return rows.getInt("id") + " | " + rows.getString("title") + " | "
                + rows.getString("author") + " | " + status;
    }

    private void requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    @FunctionalInterface
    private interface SqlAction {
        void run() throws SQLException;
    }
}

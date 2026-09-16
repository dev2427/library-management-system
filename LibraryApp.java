import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

           int choice;
if (scanner.hasNextInt()) {
    choice = scanner.nextInt();
    scanner.nextLine();
} else {
    System.out.println("Invalid input! Please enter a number.");
    scanner.nextLine(); // galat input clear karo
    continue;
}

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    // ---------- ADD BOOK ----------
    static void addBook() {
        System.out.print("Enter Book Name: ");
        String bookName = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        String query = "INSERT INTO books (book_name, author, quantity) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, bookName);
            pst.setString(2, author);
            pst.setInt(3, quantity);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Book added successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error adding book!");
            e.printStackTrace();
        }
    }

    // ---------- VIEW BOOKS ----------
    static void viewBooks() {
        String query = "SELECT * FROM books";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n--- BOOK LIST ---");
            boolean found = false;

            while (rs.next()) {
                found = true;
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                );
                System.out.println(book);
            }

            if (!found) {
                System.out.println("No books found in the library.");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching books!");
            e.printStackTrace();
        }
    }

    // ---------- UPDATE BOOK ----------
    // ---------- UPDATE BOOK ----------
static void updateBook() {
    System.out.print("Enter Book ID to update: ");
    int bookId = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter New Book Name: ");
    String bookName = scanner.nextLine();

    System.out.print("Enter New Author: ");
    String author = scanner.nextLine();

    System.out.print("Enter New Quantity: ");
    int quantity = scanner.nextInt();
    scanner.nextLine();

    String query = "UPDATE books SET book_name = ?, author = ?, quantity = ? WHERE book_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setString(1, bookName);
        pst.setString(2, author);
        pst.setInt(3, quantity);
        pst.setInt(4, bookId);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("No book found with ID " + bookId);
        }

    } catch (SQLException e) {
        System.out.println("Error updating book!");
        e.printStackTrace();
    }
}

   // ---------- DELETE BOOK ----------
static void deleteBook() {
    System.out.print("Enter Book ID to delete: ");
    int bookId = scanner.nextInt();
    scanner.nextLine();

    String query = "DELETE FROM books WHERE book_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setInt(1, bookId);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("No book found with ID " + bookId);
        }

    } catch (SQLException e) {
        System.out.println("Error deleting book!");
        e.printStackTrace();
    }
}
}   
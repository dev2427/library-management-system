public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private int quantity;

    // Naya book add karte waqt
    public Book(String bookName, String author, int quantity) {
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
    }

    // DB se fetch karte waqt
    public Book(int bookId, String bookName, String author, int quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Quantity: %d",
                bookId, bookName, author, quantity);
    }
}
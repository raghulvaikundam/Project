public class Book {
    public int isbn;
    private String title;
    private String author;
    private String ownerId;
    public int bookId;
    public boolean isAvailable;

    Book(int isbn, String title, String author, String ownerId, int bookId) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.ownerId = ownerId;
        this.isAvailable = true;
        this.bookId=bookId;
    }
    public String getAuthor()
    {
        return this.author;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getOwnerId()
    {
        return this.ownerId;
    }
}

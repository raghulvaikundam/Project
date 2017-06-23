import java.util.ArrayList;

/**
 * Created by mjeevan on 6/23/2017.
 */
public class BookListManager {
    public ArrayList<Book> bookList = new ArrayList<Book>();

    Book getBookByISBN(int isbn) {
        for (Book currentBook : bookList)
            if (currentBook.isbn == isbn && currentBook.isAvailable)
                return currentBook;
        return null;
    }
    void availBook(Book newBook) {
        bookList.add(newBook);
    }
    ArrayList<Book> listAllAvailableBooks(){
        return bookList;
    }

}

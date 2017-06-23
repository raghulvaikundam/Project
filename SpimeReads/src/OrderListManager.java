import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mjeevan on 6/23/2017.
 */

public class OrderListManager {
    public ArrayList<Order> OrderList = new ArrayList<Order>();
    enum choice{BORROW_SUCCESSFUL,INSUFFICIENT_BALANCE,BOOK_UNAVAILABLE}
    choice borrowBook(String borrowerId, int isbn, BookListManager bookListManager, ReaderListManager readerListManager, WalletListManager walletListManager,
                      DeliveryManager deliveryManager){

        Book requiredBook = bookListManager.getBookByISBN(isbn);
        Reader borrower=readerListManager.getReaderByID(borrowerId);
        if(requiredBook!=null){
            if (walletListManager.payForBook(requiredBook.bookId, borrower))
            {
                requiredBook.isAvailable=false;
                Order currentOrder=new Order(requiredBook, borrower);
                OrderList.add(currentOrder);
                deliveryManager.placeOrder(currentOrder);
                return choice.BORROW_SUCCESSFUL;
            }
            else
                return choice.INSUFFICIENT_BALANCE;
        }
        else
            return choice.BOOK_UNAVAILABLE;

    }
    HashMap<Integer, String> viewOrderStatusOfReader(Reader reader) {
       HashMap<Integer,String> orderListOfAnUser=new HashMap<Integer,String>();
        for (Order currentOrder :OrderList) {

            if (currentOrder.borrower.userId.equals(reader.userId)) {
                orderListOfAnUser.put(currentOrder.book.bookId, currentOrder.orderStatus);
            }
        }
        return orderListOfAnUser;
    }
}

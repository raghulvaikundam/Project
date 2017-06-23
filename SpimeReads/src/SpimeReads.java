import javax.jws.soap.SOAPBinding;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SpimeReads {





    public static void main(String[] args) {
        int bookIdCounter=100;
        ReaderListManager readerListManager=new ReaderListManager();
        BookListManager bookListManager=new BookListManager();
        OrderListManager orderListManager=new OrderListManager();
        WalletListManager walletListManager=new WalletListManager();
        Reader admin=new Reader("admin","admin","admin",null);
        readerListManager.addReader(admin);
        Reader currentUser = new Reader();
        PaymentGateway paymentGateway= new WalletTransaction();
        DeliveryManager deliveryManager=new DeliveryManager();
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> orderStatusMap = new HashMap<>();
        int choice;
        do {
            System.out.println();
            System.out.println("MENU");
            System.out.println("1.Register");
            System.out.println("2.User Login");
            System.out.println("3.Check User Balance");
            System.out.println("4.View my order");
            System.out.println("5.Avail Books");
            System.out.println("6.Borrow Book");
            System.out.println("7.List All available books");
            System.out.println("8.View All Orders");
            System.out.println("9.View Order Status");
            System.out.println("10.Recharge");

            System.out.println("11.Payment History");
            System.out.println("12.Exit");
            choice = scanner.nextInt();
            switch (choice) {

                case 1://register
                    System.out.println("Enter userId");
                    String userId = scanner.next();
                    System.out.println("Enter password");
                    String password = scanner.next();
                    System.out.println("Enter Name");
                    String userName = scanner.next();
                    System.out.println("Enter Address");
                    String userAddress = scanner.next();
                    Reader newReader=new Reader(userId,password,userName,userAddress);
                    if(readerListManager.addReader(newReader))
                        System.out.println("REgistration Successful");
                    else
                        System.out.println("UserId already exists!");
                    break;

                case 2://login
                    System.out.println("Enter userId");
                     userId = scanner.next();
                    System.out.println("Enter password");
                    password = scanner.next();
                    Reader newLogin = readerListManager.getReaderByID(userId);
                    if (newLogin.login(password))
                           currentUser = newLogin;
                    else
                            System.out.println("Enter Valid UserId or password");

                    break;
                case 3://checkbalance
                    System.out.print("\n" + currentUser.checkBalance());
                    break;
                case 4://view my order status
                    orderStatusMap =orderListManager.viewOrderStatusOfReader(currentUser);
                    for (Map.Entry<Integer, String> entry : orderStatusMap.entrySet()) {
                        Integer bookId = entry.getKey();
                        String orderstatus = entry.getValue();
                        System.out.println("Book Id, " + bookId + " orderstatus " + orderstatus);
                    }
                    break;
                case 5://availbook
                    System.out.println("Enter isbn");
                    int isbn = scanner.nextInt();
                    System.out.println("Enter Title");
                    String title = scanner.next();
                    System.out.println("Enter author");
                    String authorName = scanner.next();
                    Book newBook = new Book(isbn, title, authorName, currentUser.userId,bookIdCounter++);
                    bookListManager.availBook(newBook);
                    break;
                case 6://borrow books
                    System.out.println("Enter isbn");
                    isbn = scanner.nextInt();
                    OrderListManager.choice status = orderListManager.borrowBook(currentUser.userId,isbn,bookListManager,readerListManager,walletListManager ,deliveryManager);
                    if (status.ordinal()==0)
                        System.out.println("Book is borrowed successfully");
                    if (status.ordinal()==1)
                        System.out.println("Insufficient balance");
                    if (status.ordinal()==2)
                        System.out.println("Book is not available");
                    break;
                case 7://list all available books
                    System.out.println("ISBN\t- TITLE\t- AUTHOR \t- OWNERID");
                    for (Book currentBook : bookListManager.listAllAvailableBooks())
                        if (currentBook.isAvailable)
                            System.out.println(currentBook.isbn + " - " + currentBook.getTitle()
                                    + " - " + currentBook.getAuthor() + " - " + currentBook.getOwnerId());
                    break;
                case 8: //view all orders

                    if (currentUser.userId.equals("admin")) {
                    Order currentOrder;
                       for (int i = 0; i < orderListManager.OrderList.size(); i++) {

                           currentOrder=orderListManager.OrderList.get(i);
                            System.out.println("Borrower ID:" + currentOrder.borrower.userId);
                           System.out.println(" Owner ID:" + currentOrder.book.getOwnerId());
                            System.out.println("Order Status:" + currentOrder.orderStatus);
                            System.out.println(" Book Id:" + currentOrder.book.bookId);
                        }
                    }
                    break;
                case 9: //view order status
                    if (currentUser.userId.equals("admin")) {
                        System.out.println("Enter borrowers id");
                        String borrowerId = scanner.next();
                        Order currentOrder;
                        for (int i = 0; i < orderListManager.OrderList.size(); i++) {

                            currentOrder=orderListManager.OrderList.get(i);
                            if(currentOrder.borrower.userId.equals(borrowerId))
                            {
                                System.out.println("Order Status:" + currentOrder.orderStatus);
                                System.out.println(" Book Id:" + currentOrder.book.bookId);
                            }
                        }
                    }
                    break;
                case 10: //recharge wallet
                    System.out.println("Enter amount to be recharged");
                    int rechargeAmount = scanner.nextInt();
                    walletListManager.rechargeWallet(currentUser, rechargeAmount);
                    break;
                case 11://payment history
                    ArrayList<WalletTransaction> paymentHistoryList = new ArrayList<>();
                    paymentHistoryList = walletListManager.paymentHistory(currentUser.userId);
                    for (int i = 0; i < paymentHistoryList.size(); i++) {
                        System.out.println("User ID:" + paymentHistoryList.get(i).userId);
                        System.out.println("Transaction amount:" + paymentHistoryList.get(i).transactionAmount);
                        System.out.println("Transaction Description:" + paymentHistoryList.get(i).transactionDescription);
                        System.out.println("TimeStamp:" + paymentHistoryList.get(i).timestamp);
                    }
                    System.out.println("Final Balance:" + currentUser.wallet);
                    break;
                case 12:
                    break;
            }
        } while (choice != 12);
    }
}

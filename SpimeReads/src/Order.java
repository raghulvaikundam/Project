import java.util.Date;

public class Order {
   public Book book;
   public Reader borrower;
    public int deliveryAgentId;
   public String orderStatus;
    public Date dueDate;
    public Date borrowDate;

    Order(Book book,Reader borrower) {
        this.book=book;
        this.borrower=borrower;
    }

    Order() {
    }


}

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mjeevan on 6/23/2017.
 */
public class WalletListManager {
    private ArrayList<WalletTransaction> walletTransactionList = new ArrayList<WalletTransaction>();
    public boolean payForBook(int bookId, Reader borrower) {

        if (borrower.checkBalance() >= 50) {
            borrower.wallet = borrower.wallet - 50;
            WalletTransaction newTransaction = new WalletTransaction();
            newTransaction.userId = borrower.userId;
            newTransaction.transactionAmount = 50;
            newTransaction.transactionDescription = "Borrowed" + bookId;
            newTransaction.timestamp = new Date();
            walletTransactionList.add(newTransaction);
            return true;
        } else
            return false;
    }

    public ArrayList<WalletTransaction> paymentHistory(String userId) {
        ArrayList<WalletTransaction> userTranscation = new ArrayList<WalletTransaction>();
        for (WalletTransaction currentTranscation : walletTransactionList) {
            if (currentTranscation.userId.equals(userId)) {
                userTranscation.add(currentTranscation);
            }
        }
        return userTranscation;
    }

    public void rechargeWallet(Reader reader, int amount) {
        reader.wallet=reader.wallet+amount;
        WalletTransaction newTransaction = new WalletTransaction();
        newTransaction.userId = reader.userId;
        newTransaction.transactionAmount = amount;
        newTransaction.transactionDescription = "Recharge";
        newTransaction.timestamp = new Date();
        walletTransactionList.add(newTransaction);

    }
}
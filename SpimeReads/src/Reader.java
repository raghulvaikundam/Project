import java.util.HashMap;
public class Reader implements User {
    public String address;
    public int wallet;
    public String userId;
    private String password;
    public String name;

    HashMap<Integer, String> borrowedBookStatus = new HashMap<Integer, String>();
    Reader()
    {

    }

    String getPassword()
    {
        return this.password;
    }

    Reader(String userId, String password, String name, String address) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.address = address;
        this.wallet = 0;

    }

    public boolean login(String password)
    {

        if(this.password.equals(password))
            return true;
        else
            return false;
    }


    int checkBalance() {
        return this.wallet;
    }




}
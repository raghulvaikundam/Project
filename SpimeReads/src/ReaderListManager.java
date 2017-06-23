import java.util.ArrayList;

/**
 * Created by mjeevan on 6/23/2017.
 */
public class ReaderListManager {

   private ArrayList<Reader> readerList = new ArrayList<Reader>();

    Reader getReaderByID(String userId) {
        for(Reader currentreader:readerList)
        {
            if(currentreader.userId.equals(userId))
                return currentreader;
        }
       return null;

    }




    public boolean addReader(Reader reader)
    {
        if(getReaderByID(reader.userId)==null)
        {
            readerList.add(reader);
            return true;
        }
        else
            return false;
    }


}

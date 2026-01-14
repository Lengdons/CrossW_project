package Work1;
import java.sql.Connection;
import java.util.ArrayList;
public class Connect {

	public static void startFromDatabase(Connection c, String lvl, int n) {
        try {
            ArrayList<DictionaryEntry> data =
                    DictionaryDAO.getByLevel(c, lvl, n);
}

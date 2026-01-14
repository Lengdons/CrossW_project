package Work1;

import java.sql.Connection;
import java.util.ArrayList;

public class Service {

    public static void startFromDatabase(Connection c, String lvl, int n) {

        try {
            
            ArrayList<Entry> data = Dictionary.getByLevel(c, lvl, n);

           
            ArrayList<String> words = new ArrayList<>();
            String defs = "";

            for (int i = 0; i < data.size(); i++) {
                words.add(data.get(i).getWord());
                defs += (i + 1) + ". " + data.get(i).getDef() + "\n";
            }

           
            CrosswordGrid.grid(words, defs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
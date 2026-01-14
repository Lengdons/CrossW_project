package Work1;

import java.sql.Connection;
import java.util.ArrayList;

public class Service {

    public static void startFromDatabase(Connection c, String[] levels, int n)
            throws Exception {

        ArrayList<Entry> data = Dictionary.getByLevels(c, levels, n);

        ArrayList<String> words = new ArrayList<>();
        StringBuilder questions = new StringBuilder("Jautājumi:\n\n");

        for (int i = 0; i < data.size(); i++) {
            words.add(data.get(i).getWord());
            questions.append(i + 1)
                     .append(". ")
                     .append(data.get(i).getDef())
                     .append("\n");
        }

        Grid.grid(words, questions.toString());
    }
}

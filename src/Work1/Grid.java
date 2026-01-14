package Work1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Grid {

    public static void grid(ArrayList<String> words, String defs) {

        // Parāda visus jautājumus
        JOptionPane.showMessageDialog(
                null,
                "Jautājumi:\n\n" + defs,
                "Krustvārdu mīkla",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Prasa atbildes pa vienam
        for (int i = 0; i < words.size(); i++) {

            String input = JOptionPane.showInputDialog(
                    "Jautājums " + (i + 1) +
                    "\nIevadi atbildi (" + words.get(i).length() + " burti)"
            );

            if (input == null) return; // Cancel = iziet

            if (input.equalsIgnoreCase(words.get(i))) {
                JOptionPane.showMessageDialog(null, "Pareizi ✅");
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Nepareizi \nPareizā atbilde: " + words.get(i)
                );
            }
        }

        JOptionPane.showMessageDialog(null, "Spēle pabeigta ");
    }
}

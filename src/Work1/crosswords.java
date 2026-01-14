package Work1;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class crosswords {

    public static void main(String[] args) {

        Connection conn = sqldatabase.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Nav DB savienojuma!");
            return;
        }

        String choice = JOptionPane.showInputDialog(
                "Izvēlies grūtību:\n" +
                "1 - Viegli (10)\n" +
                "2 - Vidēji (30)\n" +
                "3 - Grūti (50)");

        if (choice == null || !choice.matches("[123]")) return;

        String[] levels;
        int count;

        if (choice.equals("1")) {
            levels = new String[]{"viegli"};
            count = 10;
        } else if (choice.equals("2")) {
            levels = new String[]{"viegli", "videji"};
            count = 30;
        } else {
            levels = new String[]{"gruti"};
            count = 50;
        }

        try {
            Service.startFromDatabase(conn, levels, count);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kļūda!");
        }
    }
}

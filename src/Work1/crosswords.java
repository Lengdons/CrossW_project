package Work1;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class crosswords {

    public static void main(String[] args) {

        Connection conn = sqldatabase.getConnection();
        if (conn == null) {
            JOptionPane.showMessageDialog(null, "Nav DB savienojuma!");
            return;
        }

        String lvl = JOptionPane.showInputDialog(
                "Izvēlies grūtību:\n1 - Viegli\n2 - Vidēji\n3 - Grūti");

        if (lvl == null || !lvl.matches("[123]")) return;

        try {
           Service.startFromDatabase(conn, lvl, 10);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Kļūda!");
        }
    }
}

package Work1;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class crosswords {
public static void main(String[] args) {
Connection conn = sqldatabase.getConnection();
 if (conn == null) return;
	  
String inp = JOptionPane.showInputDialog(
 "Izvēlies grūtību:\n1 - Viegli\n2 - Vidēji\n3 - Grūti");

	 if (inp == null) return;

	 String lvl =
	  inp.equals("1") ? "viegli" :
	  inp.equals("2") ? "videji" : "gruti";
CrosswordService.startFromDatabase(conn, lvl, 10);
	    }
	}

	  

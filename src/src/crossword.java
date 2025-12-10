package src;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import db.database;

import javax.swing.JOptionPane;

public class crossword {

	public static void veidot() {
		StringBuilder build = new StringBuilder();
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				build.append("⬜ \t");
			}
			build.append("\n");
		}
		JOptionPane.showMessageDialog(null, build.toString());
	}
	
	public static void izveleties() {
		String a = JOptionPane.showInputDialog(null, "1 - Vieglu 2 - Videju 3 - Grutu");
		
	}
	
	public static void main(String[] args) {
		
		Connection conn = database.getConnection();
		if (conn != null) {
            System.out.println("Savienojums veiksmigs");
        }
		
		String a = JOptionPane.showInputDialog(null, "1 - Veidot savu crossword \n2 - Izveleties gatavu crossword");
		
		switch (a) {
		case "1": 
			veidot();
		break;
		case "2":
			izveleties();
		break;
	}
}
}

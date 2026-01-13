package core;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Window extends JFrame{

	private char[][] atbildesBoard;
	
	public Window(char[][] gatavaisBoard) {
		this.atbildesBoard = gatavaisBoard;
		
	initialize();
	}
	
	public void initialize() {
		setTitle("Krustvārdu Mīkla");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 800);
		setLocationRelativeTo(null);
		
		setLayout(new GridLayout(20, 20)); //20X20 lauks //velak varbut samainit uz pielāgojamu izmēru

		for (int i = 0; i < atbildesBoard.length; i++) {
		    for (int j = 0; j < atbildesBoard[i].length; j++) {
		        char letter = atbildesBoard[i][j];
		        
		        JTextField cell = new JTextField();
		        cell.setHorizontalAlignment(SwingConstants.CENTER); // teksta nocentrēšana
		        
		        if (letter == '-') {
		            cell.setBackground(java.awt.Color.GRAY);
		            cell.setEditable(false);
		        } else {
		            cell.setBackground(java.awt.Color.WHITE);
		            cell.setEditable(true);
		        }
		        add(cell);
		    }
		}
		
	}
}

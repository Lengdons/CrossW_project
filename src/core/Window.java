package core;

import javax.swing.JFrame;

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
		
	}
}

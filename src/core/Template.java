package core;

import javax.swing.JFrame;

public class Template extends JFrame{

	public Template() {
	initialize();
	}
	
	public void initialize() {
		setTitle("Krustvārdu Mīkla");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 500);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
}

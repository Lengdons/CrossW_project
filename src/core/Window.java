package core;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		JPanel gridPanel = new JPanel(new GridLayout(20, 20)); //20X20 lauks //velak varbut samainit uz pielāgojamu izmēru

		for (int i = 0; i < atbildesBoard.length; i++) {
		    for (int j = 0; j < atbildesBoard[i].length; j++) {
		        char letter = atbildesBoard[i][j];
		        
		        JTextField cell = new JTextField();
		        cell.setPreferredSize(new Dimension(20, 20));
		        cell.setHorizontalAlignment(SwingConstants.CENTER); // teksta nocentrēšana
		        if (letter == '-') {
		            cell.setBackground(java.awt.Color.GRAY);
		            cell.setEditable(false);
		        } else {
		            cell.setBackground(java.awt.Color.WHITE);
		            cell.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
		            cell.setEditable(true);
		        }
		        gridPanel.add(cell);
		    }
		}
		JPanel wrapper = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		wrapper.add(gridPanel, gbc);
		add(wrapper);
	}
}

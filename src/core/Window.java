package core;

import java.awt.BorderLayout;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import core.KrustvarduMikla;
import core.placedWord;

public class Window extends JFrame{

	private char[][] atbildesBoard;
	private JTextField[][] lauks; 
	private java.util.List<placedWord> vardiList;
	
	public Window(KrustvarduMikla game) {
        this.atbildesBoard = game.getBoard();     
        this.vardiList = game.getPlacedWords();
		
	initialize();
	
	}
	public void checkA() {
	    for (int i = 0; i < atbildesBoard.length; i++) {
	        for (int j = 0; j < atbildesBoard[i].length; j++) {
	            if (atbildesBoard[i][j] != '-') {
	                String minejums = lauks[i][j].getText().trim().toUpperCase();
	                String atbilde = String.valueOf(atbildesBoard[i][j]);

	                if (minejums.equals(atbilde)) {
	                    lauks[i][j].setBackground(java.awt.Color.GREEN);
	                } else if (!minejums.isEmpty()) {
	                    lauks[i][j].setBackground(java.awt.Color.YELLOW);
	                }
	            }
	        }
	    }
	}
	
	public void initialize() {
		setTitle("Krustvārdu Mīkla");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 900); 
	    setLocationRelativeTo(null);
	    setResizable(false);
		
		JPanel topPanel = new JPanel(new java.awt.GridBagLayout());
		topPanel.setBackground(java.awt.Color.WHITE);
		
		JPanel gamePanel = new JPanel(new GridLayout(20, 20)); //20X20 lauks //velak varbut samainit uz pielāgojamu izmēru
		gamePanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
		
		lauks = new JTextField[atbildesBoard.length][atbildesBoard[0].length];
		
		for (int i = 0; i < atbildesBoard.length; i++) {
	        for (int j = 0; j < atbildesBoard[i].length; j++) {
	            char letter = atbildesBoard[i][j];
	            JTextField cell = new JTextField();
	            cell.setPreferredSize(new Dimension(20, 20));
	            cell.setHorizontalAlignment(SwingConstants.CENTER);
	            cell.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

	            if (letter == '-') {
	                cell.setBackground(java.awt.Color.GRAY);// šuna
	                cell.setEditable(false);
	                cell.setBorder(null); //atslēgt tukšos laukus
	            } else {
	            		cell.setBackground(java.awt.Color.WHITE);
	                cell.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
	                cell.setEditable(true);
	            }

	            lauks[i][j] = cell;
	            gamePanel.add(cell); 
	        }
	    }
	    topPanel.add(gamePanel);
	    
	    JPanel bottomPanel = new JPanel(new BorderLayout());

//definiciju lauks
	    JTextArea definitionsArea = new JTextArea();
	    definitionsArea.setText("Definīcijas:\nHorizontāli:\n\nVertikāli:"); 
	    definitionsArea.setEditable(false);
	    definitionsArea.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
	    definitionsArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
	    bottomPanel.add(new JScrollPane(definitionsArea), BorderLayout.CENTER);

	    JPanel buttonPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
	    
	    JButton checkPoga = new JButton("Pārbaudīt");
	    checkPoga.addActionListener(e -> checkA());
	    checkPoga.setPreferredSize(new Dimension(120, 30));
	    
	    JButton exitPoga = new JButton("Iziet");
	    exitPoga.setPreferredSize(new Dimension(100, 30));
	    exitPoga.addActionListener(e -> System.exit(0)); 

	    buttonPanel.add(checkPoga);
	    buttonPanel.add(exitPoga);
	    
	    bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

	    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
	    splitPane.setDividerLocation(410); 
	    splitPane.setEnabled(false);       
	    splitPane.setDividerSize(1);       

	    add(splitPane);
	}
}

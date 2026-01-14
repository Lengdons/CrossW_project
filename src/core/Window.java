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
	private core.CrosswordCell[][] lauks; 
	private java.util.List<placedWord> vardiList;
	private java.util.Map<String, String> dictionary; 
	private boolean isHorizontal = true;
	
	public Window(KrustvarduMikla game, java.util.Map<String, String> dictionary) {
        this.atbildesBoard = game.getBoard();     
        this.vardiList = game.getPlacedWords();
        this.dictionary = dictionary;
		
	initialize();
	
	}
	public void checkA() {
		boolean allCorrect = true;
	    for (int i = 0; i < atbildesBoard.length; i++) {
	        for (int j = 0; j < atbildesBoard[i].length; j++) {
	            if (atbildesBoard[i][j] != '-') {
	                String minejums = lauks[i][j].getText().trim().toUpperCase();
	                String atbilde = String.valueOf(atbildesBoard[i][j]);

	                if (minejums.equals(atbilde)) {
	                    lauks[i][j].setBackground(java.awt.Color.GREEN);
	                } else {
	                	allCorrect = false;
	                	if (!minejums.isEmpty()) {
	                    lauks[i][j].setBackground(java.awt.Color.YELLOW);
	                } else {
                        lauks[i][j].setBackground(java.awt.Color.WHITE);
	                }
	            }
	        }
	    }
	}

	    if (allCorrect) {
	        javax.swing.JOptionPane.showMessageDialog(this, "Apsveicam laimētājus!");
	    }
}
	
	public void initialize() {
		StringBuilder sb = new StringBuilder();
	    java.util.Map<String, Integer> wordNumbers = setupWordOrdering(sb);
	    
		vardiList.sort((a, b) -> (a.row != b.row) ? a.row - b.row : a.col - b.col);
		setTitle("Krustvārdu Mīkla");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 900); 
	    setLocationRelativeTo(null);
	    setResizable(false);
		
		JPanel topPanel = new JPanel(new java.awt.GridBagLayout());
		topPanel.setBackground(java.awt.Color.WHITE);
		
		JPanel gamePanel = new JPanel(new GridLayout(20, 20)); //20X20 lauks //velak varbut samainit uz pielāgojamu izmēru
		gamePanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
		
		lauks = new core.CrosswordCell[atbildesBoard.length][atbildesBoard[0].length];
	//secības metode
		for (int i = 0; i < atbildesBoard.length; i++) {
	        for (int j = 0; j < atbildesBoard[i].length; j++) {
	        	
	        	char letter = atbildesBoard[i][j];

	     core.CrosswordCell cell = new core.CrosswordCell();
	     
	  Dimension size = new Dimension(25, 25);
	  	 cell.setPreferredSize(size);
	  	 cell.setMinimumSize(size); 
	  	 cell.setMaximumSize(size);

	  	 cell.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	  	 cell.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
	  	 cell.setDocument(new core.CharLimit(1));
	     cell.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	     cell.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));

	     cell.setDocument(new core.CharLimit(1));

	     final int r = i;
	     final int c = j;

	     cell.addKeyListener(new java.awt.event.KeyAdapter() {
	         @Override
	         public void keyReleased(java.awt.event.KeyEvent e) {
	             if (Character.isLetterOrDigit(e.getKeyChar())) {
	                 if (isHorizontal) {
	                     if (c + 1 < lauks[0].length && lauks[r][c + 1].isEditable()) lauks[r][c + 1].requestFocus();
	                 } else {
	                     if (r + 1 < lauks.length && lauks[r + 1][c].isEditable()) lauks[r + 1][c].requestFocus();
	                 }
	             }
	             if (e.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE) {
	                 if (isHorizontal) {
	                     if (c - 1 >= 0 && lauks[r][c - 1].isEditable()) lauks[r][c - 1].requestFocus();
	                 } else {
	                     if (r - 1 >= 0 && lauks[r - 1][c].isEditable()) lauks[r - 1][c].requestFocus();
	                 }
	             }
	             if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
	                 isHorizontal = !isHorizontal;
	             }
	         }
	     });

	     cell.addMouseListener(new java.awt.event.MouseAdapter() {
	         @Override
	         public void mouseClicked(java.awt.event.MouseEvent e) {
	             if (e.getClickCount() == 2) {
	                 isHorizontal = !isHorizontal;
	             }
	         }
	     });
	     String cellNum = "";
	     for (core.placedWord pw : vardiList) {
	         if (pw.row == i && pw.col == j && wordNumbers.containsKey(pw.word)) {
	             cellNum = String.valueOf(wordNumbers.get(pw.word));
	         }
	     }

	     if (letter == '-') {
	         cell.setBackground(java.awt.Color.GRAY);
	         cell.setEditable(false);
	         cell.setBorder(null);
	     } else {
	         cell.setBackground(java.awt.Color.WHITE);
	         cell.setEditable(true);

	         if (!cellNum.isEmpty()) {
	             cell.setCellNumber(cellNum);
	         }
	         
	         cell.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
	     }

	     lauks[i][j] = cell;
	     gamePanel.add(cell);
	        }
	    }
	    topPanel.add(gamePanel);
	    
	    JPanel bottomPanel = new JPanel(new BorderLayout());
//definiciju lauks
	    JTextArea definitionsArea = new JTextArea();
	    
	    definitionsArea.setText(sb.toString()); 
	    definitionsArea.setEditable(false);
	    definitionsArea.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
	    definitionsArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
//pogas
	    bottomPanel.add(new JScrollPane(definitionsArea), BorderLayout.CENTER);

	    JPanel buttonPanel = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
	    
	    JButton checkPoga = new JButton("Pārbaudīt");
	    checkPoga.addActionListener(e -> checkA());
	    checkPoga.setPreferredSize(new Dimension(100, 30));
	    
	    JButton exitPoga = new JButton("Iziet");
	    exitPoga.setPreferredSize(new Dimension(100, 30));
	    exitPoga.addActionListener(e -> System.exit(0)); 

	    buttonPanel.add(checkPoga);
	    buttonPanel.add(exitPoga);
	    bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

	    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
	    splitPane.setDividerLocation(510); 
	    splitPane.setEnabled(false);       
	    splitPane.setDividerSize(2);       

	    add(splitPane);
	}
	
// Metode order
	private java.util.Map<String, Integer> setupWordOrdering(StringBuilder sb) {
	    vardiList.sort((a, b) -> (a.row != b.row) ? a.row - b.row : a.col - b.col);

	    java.util.Map<String, Integer> map = new java.util.HashMap<>();
	    int counter = 1;

	    sb.append("HORIZONTĀLI:\n");
	    for (core.placedWord pw : vardiList) {
	        if (!pw.isVertical) {
	            sb.append(String.format("%d. %s\n", counter, dictionary.get(pw.word)));
	            map.put(pw.word, counter);
	            counter++;
	        }
	    }

	    sb.append("\nVERTIKĀLI:\n");
	    for (core.placedWord pw : vardiList) {
	        if (pw.isVertical) {
	            sb.append(String.format("%d. %s\n", counter, dictionary.get(pw.word)));
	            map.put(pw.word, counter);
	            counter++;
	        }
	    }
	    return map;
	}
}

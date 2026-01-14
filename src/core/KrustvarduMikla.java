package core;

import java.util.List;

public class KrustvarduMikla {
	
	 private char[][] board;
	    private List<placedWord> placedWords;

	    public KrustvarduMikla(char[][] board, List<placedWord> placedWords) {
	        this.board = board;
	        this.placedWords = placedWords;
	    }

	    public char[][] getBoard() { return board; }
	    public List<placedWord> getPlacedWords(){ return placedWords; }
	    
	}


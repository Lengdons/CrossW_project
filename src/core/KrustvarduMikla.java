package core;

import java.util.List;

public class KrustvarduMikla {
	
	 private char[][] board;
	    private List<placedWord> placedWords;
	    private List<String> failedWords;

	    public KrustvarduMikla(char[][] board, List<placedWord> placedWords, List<String> failedWords) {
	        this.board = board;
	        this.placedWords = placedWords;
	        this.failedWords = failedWords;
	    }

	    public char[][] getBoard() {
	    	return board; 
	    }
	    public List<placedWord> getPlacedWords(){
	    	return placedWords;
	    }
	    public List<String> getFailedWords(){
	    	return failedWords;
	    }
	    
	}


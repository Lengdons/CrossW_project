package core;

import java.util.List;

public class CrosswordGenerator {
	//Risinājuma lauks

//Vertical collision check
	private boolean canPlaceVertical(char[][] board, String word, int startRow, int startCol) {
        
		if(word.length()+startRow<board.length){
			if(startRow > 0 && board[startRow-1][startCol] != '-') { //top check
				return false;
			}
			for(int i=0; i<word.length(); i++) {
				char boardChar = board[startRow + i][startCol];
				char wordChar = word.charAt(i);
				
				if(boardChar == '-') {

				if(startCol > 0 && board[startRow + i][startCol - 1] != '-')//check left
					return false;
				if(startCol < board.length - 1 && board[startRow + i][startCol + 1] != '-') // check right
					return false;
				
				}else{
					if (boardChar != wordChar) //burtu pārklāšanās
					return false;
				}
			}
		}else return false;
			if(startRow+word.length()<board.length && board[startRow+word.length()][startCol] != '-') { //check bottom
				return false;
			}else{
				return true;
				}
    }
	
//Horizontal collision check
	private boolean canPlaceHorizontal(char[][] board, String word, int startRow, int startCol) {
		
		if(word.length()+startCol<board.length){
			if(startCol > 0 && board[startRow][startCol - 1] != '-') { //check left
				return false;
			}
			for(int i=0; i<word.length(); i++){
				char boardChar = board[startRow][startCol + i];
				char wordChar = word.charAt(i);
				
				if(boardChar == '-') {

				if(startRow > 0 && board[startRow - 1][startCol + i] != '-')//check top
					return false;
				if(startRow < board.length - 1 && board[startRow + 1][startCol + i] != '-') // check bottom
					return false;
				}else{
					if (boardChar != wordChar)//burtu pārklāšanās
					return false;
				}
			}
		}else return false;
			if(startCol+word.length()<board.length && board[startRow][startCol+word.length()] != '-') { //check right
				return false;
			}else{
				return true;
				}
	}

//Ievieto vārdu
	public boolean placeWord(char[][] board, String word, int r, int c, boolean isVertical) {
        if (isVertical) {
            if (canPlaceVertical(board, word, r, c)) {
                for (int i = 0; i < word.length(); i++) board[r + i][c] = word.charAt(i);
                return true;
            }
        } else {
            if (canPlaceHorizontal(board, word, r, c)) {
                for (int i = 0; i < word.length(); i++) board[r][c + i] = word.charAt(i);
                return true;
            }
        }
        return false;
    }
	
	public char[][] generate(List<String> WORDS, int size) {
		char[][] board = new char[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				board[i][j] = '-';
				}	
		}
// Pirmā vārda enkurs (horizontāli)
		String firstWord = WORDS.get(0);
			
		int startRow = size/2;
		int startCol = (size/2) - (firstWord.length()/2);
		
		placeWord(board, firstWord, startRow, startCol, false);
		
// 		Pirmā vārda enkurs (vertikāli)
//	int startRow = (size/2) - (firstWord.length()/2);
//	int startCol = size/2;
//	placeWord(board, firstWord, startRow, startCol, true);
		
		for (int i = 1; i < WORDS.size(); i++) {
		    String currentWord = WORDS.get(i);
		    boolean placed = false;

		    for (int r = 1; r < size && !placed; r++) { 
		        for (int c = 1; c < size && !placed; c++) {
		            if (placeWord(board, currentWord, r, c, true)) {
		                placed = true;
		            } 
		            else if (placeWord(board, currentWord, r, c, false)) {
		                placed = true;
		            }
		        }
		    }
		}
		return board;
	}
	
	
}

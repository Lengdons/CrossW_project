package core;
//Gustavs
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrosswordGenerator {

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
	
	public KrustvarduMikla generate(List<String> words, int size) {
	    char[][] board = new char[size][size];
	    List<placedWord> placedList = new ArrayList<>();
	    Random rand = new Random();

	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) board[i][j] = '-';
	    }

	    if (!words.isEmpty()) {
	        String first = words.get(0);
	        int startRow = size / 3;
	        int startCol = (size / 3) - (first.length() / 2);
	        
	        placeWord(board, first, startRow, startCol, false);
	        placedList.add(new placedWord(first, startRow, startCol, false));
	    }
	    
	    for (int i = 1; i < words.size(); i++) {
	        String wordToPlace = words.get(i);
	        boolean placed = false;
	        
	        java.util.Collections.shuffle(placedList);
	        
	        searchLoop:
	        for (placedWord existing : placedList) {
	            String existingWord = existing.word;
	            
// kopīgais burts ?
	            for (int j = 0; j < wordToPlace.length(); j++) {
	                char letterNew = wordToPlace.charAt(j);

	                for (int k = 0; k < existingWord.length(); k++) {
	                    char letterExisting = existingWord.charAt(k);
//ja kopīgs
	                    if (letterNew == letterExisting) {
	                    	
	                        int intersectRow = existing.row + (existing.isVertical ? k : 0);
	                        int intersectCol = existing.col + (existing.isVertical ? 0 : k);

	                        boolean newIsVertical = !existing.isVertical;
	                        
	                        int newStartRow = intersectRow - (newIsVertical ? j : 0);
	                        int newStartCol = intersectCol - (newIsVertical ? 0 : j);
	                        
	                        if (newStartRow < 0 || newStartCol < 0 || 
	                        	    (newIsVertical ? newStartRow + wordToPlace.length() : newStartRow) > board.length ||
	                        	    (!newIsVertical ? newStartCol + wordToPlace.length() : newStartCol) > board.length) {
	                        	    continue; 
	                        	}

	                        if (placeWord(board, wordToPlace, newStartRow, newStartCol, newIsVertical)) {
	                            placedList.add(new placedWord(wordToPlace, newStartRow, newStartCol, newIsVertical));
	                            placed = true;
	                            break searchLoop;
	                            
	                        }
	                    }
	                }
	            }
	        }
	    }

	    return new KrustvarduMikla(board, placedList);
	}
}
	


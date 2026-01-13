package core;

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
	
	
}

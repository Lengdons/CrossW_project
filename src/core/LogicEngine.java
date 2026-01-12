package core;

import java.util.Scanner;

public class LogicEngine {
	char[][] board = new char[20][20];
	Scanner sc = new Scanner(System.in);
	
	
// Grid/board 20x20
public LogicEngine() {	
	for(int i=0; i<20; i++) {
		for(int j=0; j<20; j++) {
			board[i][j] = '-';
			}	
		}
	for(int i=0;i<10;i++) {
		String word = sc.next();
		ScanField(word);
	}
	printBoard();
 
}
public boolean canPlaceVertical(String word, int startRow, int startCol) {
	//Vertikālais collision ckeck loop
	if(word.length()+startRow<board.length){
		if(startRow > 0 && board[startRow-1][startCol] != '-') {
			return false;
		}
		for(int i=0; i<word.length(); i++) {
			if(board[startRow+i][startCol] != word.charAt(i) && board[startRow+i][startCol] != '-' 
					&& startCol<board.length && board[startRow+i][startCol+1] != '-'){	//side check right
				return false;}
			if(startCol>0 && board[startRow+i][startCol-1] != '-') { //side check left
				return false;
			}
				}
		if(startRow+word.length()<board.length && board[startRow+word.length()][startCol] != '-') {
			return false;
		}else{
			return true;
			}
		}else{
			return false;
		}
	}

public boolean canPlaceHorizontal(String word, int startRow, int startCol) {
	//Horizontāli collision ckeck loop
	if(word.length()+startCol<board.length){
		for(int i=0; i<word.length(); i++){
			if(board[startRow][startCol+i] != word.charAt(i) && board[startRow][startCol+i] != '-'){	
				return false;}
				}
		if(startCol+word.length()<board.length && board[startRow][startCol+word.length()] != '-') {
			return false;
		}else{
			return true;
			}
		}else{
			return false;
		}
	}
public void PlaceVertical(String word, int startRow, int startCol) {
	// Ievieto Vertikāli
		for(int i=0; i<word.length(); i++){
			board[startRow+i][startCol] = word.charAt(i);
				}
			}

public void PlaceHorizontal(String word, int startRow, int startCol) {
	// Ievieto Horizontāli
		for(int i=0; i<word.length(); i++){
			board[startRow][startCol+i] = word.charAt(i);
				}
			}

public void ScanField(String word) {
	for(int i=0; i<board.length; i++) {
		for (int j=0;j<board.length; j++) {
			if(canPlaceVertical(word, i, j)) {
				PlaceVertical(word, i, j);
				return;
				}else if(canPlaceHorizontal(word,i,j)) {
				PlaceHorizontal(word,i,j);
				return;
					}
				}
			}
		}
		
public void printBoard() {
	for(int i=0; i<20; i++) {
		for(int j=0; j<20; j++) {
	System.out.print(board[i][j] + " ");
			}System.out.println();
		}
	}

public static void main(String[] args) {
    new LogicEngine();
}
}
	


	
	


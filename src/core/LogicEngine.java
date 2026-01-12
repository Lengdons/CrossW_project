package core;

import java.util.Scanner;

public class LogicEngine {
	char[][] board = new char[20][20];
	Scanner sc = new Scanner(System.in);
	String a = sc.next();
	char[]letters = a.toCharArray();
	
// Grid/board 20x20
public void LogicEngine() {	
	for(int i=0; i<20; i++) {
		for(int j=0; j<20; j++) {
			board[i][j] = '-';
			}	
		}
	// Novieto 'vardu vertikāli
	if(canPlaceVertical(a, 5, 2)) {
		PlaceVertical(a, 5, 2);
	} else {
		System.out.println("Collision detected!");
	}
}
public boolean canPlaceVertical(String word, int startRow, int startCol) {
	//Vertikālais collision ckeck loop
	if(word.length()+startRow<board.length){
		for(int i=0; i<word.length(); i++) {
			if(board[startRow+i][startCol] != word.charAt(i) && board[startRow+i][startCol] != '-'){	
				return false;}
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

public void ScanField() {
	for(int i=0; i<board.length; i++) {
		for (int j=0;j<board.length; j++) {
			if(canPlaceVertical(a, i, j)) {
				PlaceVertical(a, i, j);
				return;
				}else {
			if(canPlaceHorizontal(a,i,j)) {
				PlaceHorizontal(a,i,j);
				return;
					}
				}
			}
		}
	}	

}

	


	
	


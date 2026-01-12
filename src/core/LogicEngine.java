package core;

import java.util.Scanner;

public class LogicEngine {
	char[][] board = new char[15][15];
	Scanner sc = new Scanner(System.in);
	String a = sc.next();
	char[]letters = a.toCharArray();
	

public void LogicEngine() {	
	for(int i=0; i<15; i++) {
		for(int j=0; j<15; j++) {
			board[i][j] = '-';
			}	
		}
//horizontal loop
	for(int j=0;j<a.length();j++) {
		board[0+5][j+2] = letters[j];
	}
//vetical loop
	for(int i=0; i<a.length(); i++) {
		board[i+5][0+2] = letters[i];
	}		
}
public boolean canPlaceVertical(String word, int startRow, int startCol) {
	//konktrēta collison loop
	
		for(int i=0; i<word.length(); i++) {
			if(board[startRow+i][startCol] != word.charAt(i) && board[startRow+i][startCol] != '-'){	
				return false;}
				}
		if(startRow+word.length()<15)(board[startRow+word.length()][startCol] != '-') {
			return false;
		}else{
		return true;
		}
	}

}

	


	
	


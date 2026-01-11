package core;

import java.util.Scanner;

public class LogicEngine {
	char[][] board = new char[15][15];
	
	

public void LogicEngine() {
	Scanner sc = new Scanner(System.in);
	System.out.println("vārds");
	String a = sc.next();
	char[]letters = a.toCharArray();
	
	for(int i=0; i<15; i++) {
		for(int j=0; j<15; j++) {
			board[i][j] = '-';
			}	
		}
	board[0][0] = 'J';
	}
	
	}
	


	
	


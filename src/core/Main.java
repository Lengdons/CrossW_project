package core;

import java.util.ArrayList;
import java.util.List;

public class Main {

   public static void main(String[] args) {
	   
	   List<String> wL = new ArrayList<>();
	   
	   wL.add("Introduction");
	   wL.add("Review");
	   wL.add("Electorate");
	   wL.add("Orange");
	   wL.add("Fruitful");
	   wL.add("Monetise");
	   wL.add("Blueberry");
	   wL.add("Accord");
	   wL.add("Ignite");
	   wL.add("Sail");
	   wL.add("Kite");
	   
	  wL.replaceAll(String::toUpperCase);
	   
		
	   CrosswordGenerator builder = new CrosswordGenerator();
	char[][] board = builder.generate(wL, 20);
	
	print(board);

//inicializē JFrame logu
	Window logs = new Window(board); 
	logs.setVisible(true);
	
	}
   public static void print(char[][] board) {
	   for (int i = 0; i < board.length; i++) {
           for (int j = 0; j < board[i].length; j++) {
               System.out.print(board[i][j] + " ");
   }
           System.out.println();
	   }
   }
   
}

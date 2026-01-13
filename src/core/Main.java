package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

   public static void main(String[] args) {
	   
	//directory
	    Map<String, String> dictionary = new HashMap<>();
	    
	    dictionary.put("INTRODUCTION", "The beginning section of a book or speech");
	    dictionary.put("REVIEW", "A formal assessment or critique");
	    dictionary.put("ELECTORATE", "All the people in a country who are entitled to vote");
	    dictionary.put("ORANGE", "A citrus fruit or a color");
	    dictionary.put("FRUITFUL", "Producing good or helpful results");
	    dictionary.put("MONETISE", "To earn revenue from an asset");
	    dictionary.put("BLUEBERRY", "A small sweet blue-black edible berry");
	    dictionary.put("ACCORD", "An official agreement or treaty");
	    dictionary.put("IGNITE", "To catch fire or cause to catch fire");
	    dictionary.put("SAIL", "Fabric used to catch wind and propel a boat");
	    dictionary.put("KITE", "A toy that flies in the wind on the end of a string");

	    List<String> wL = new ArrayList<>(dictionary.keySet());
//izveido spēles objektu
	    CrosswordGenerator builder = new CrosswordGenerator();
	    KrustvarduMikla game = builder.generate(wL, 20);

// nodot Speles objektu un directory uz Window.java
	    Window logs = new Window(game, dictionary); 
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

package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	
        // 1. Setups
    	
        int gridSize = 20;
        CrosswordGrid grid = new CrosswordGrid(gridSize);
        CrosswordGenerator generator = new CrosswordGenerator();
        List<CrosswordGenerator.PlacedWord> solvedList = new ArrayList<>();

        // 2. The Word List 
        // Samaini šito arraylistu ar datiem no SQL
        
        List<String> wordList = Arrays.asList("SQL");
        
        // Sašķiro no īsākā uz garāko vārdu 
        wordList.sort((s1, s2) -> s2.length() - s1.length());

        // 3. Run 
        System.out.println("Generating Crossword...");
        boolean success = generator.generate(grid, wordList, solvedList);

        if (success) {
            System.out.println("Success! Final Board:");
            grid.printGrid();
            
            //4. output data
            for(CrosswordGenerator.PlacedWord pw : solvedList) {
                System.out.println("Placed " + pw.word + " at [" + pw.row + "," + pw.col + "] " + (pw.isVertical ? "Down" : "Across"));
            }
        } else {
            System.out.println("Could not generate a valid crossword with this list.");
        }
    }
}